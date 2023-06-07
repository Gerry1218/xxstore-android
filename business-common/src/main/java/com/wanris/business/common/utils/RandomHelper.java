package com.wanris.business.common.utils;

import java.util.Random;

/**
 * Description:随机数生成工具
 *
 * @Author: Gerry
 * @Date: 2020/12/12
 */
public class RandomHelper {

    /**
     * 生成随机数
     *
     * @param max
     * @return
     */
    public static int getRandom(int max) {
        try {
            Random random = new Random();
            return random.nextInt(max);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 生成10位随机数
     *
     * @return
     */
    public static String getRandom10(int number) {
        String strRand = "";
        for (int i = 0; i < number; i++) {
            strRand += String.valueOf((int) (Math.random() * 10));
        }
        return strRand;
    }
    /**
     * 生成随机小写字母字符串
     * @return
     */
    public static String getSmallLetter(int size){
        StringBuffer buffer = new StringBuffer();
        Random random = new Random();
        for(int i=0; i<size;i++){
            buffer.append((char) (random.nextInt(26) + 'a'));
        }
        return buffer.toString();
    }
    /**
     * 生成随机大写字母字符串
     * @return
     */
    public static String getLargeLetter(int size){
        StringBuffer buffer = new StringBuffer();
        Random random = new Random();
        for(int i=0; i<size;i++){
            buffer.append((char) (random.nextInt(26) + 'A'));
        }
        return buffer.toString();
    }
    /**
     * 数字与大小写字母混编字符串
     * @param size
     * @return
     */
    public static String getNumLargeSmallLetter(int size){
        StringBuffer buffer = new StringBuffer();
        Random random = new Random();
        for(int i=0; i<size;i++){
            if(random.nextInt(2) % 2 == 0){//字母
                if(random.nextInt(2) % 2 == 0){
                    buffer.append((char) (random.nextInt(26) + 'A'));
                }else{
                    buffer.append((char) (random.nextInt(26) + 'a'));
                }
            }else{//数字
                buffer.append(random.nextInt(10));
            }
        }
        return buffer.toString();
    }
}
