package com.wanris.library.resource;

/**
 * Description: 生成不同分辨率的文件
 *
 * @Author: Gerry
 * @Date: 2022/12/12
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Gerry on 2022/12/12
 * 生成适配dimen的文件
 * <p>
 * 将defaultDp更改成默认的dp值，便可以生成相应的配置文件
 */
public class DimenUtil {
    // 高保真分辨率 720X1280   320dpi  sw360
    // 默认dimension 屏幕 dp值 ,defaultDp
    // 高保真分辨率 750X1334   320dpi  sw375
    public final static int defaultDp = 375;

    public static void main(String[] args) {
        Davn();
    }

    public static void Davn() {
        // 以此文件夹下的dimens.xml文件内容为初始值参照
        File file = new File("./library-resource/src/main/res/values/dimens.xml");

        BufferedReader reader = null;
        StringBuilder sw240 = new StringBuilder();
        StringBuilder sw320 = new StringBuilder();
        StringBuilder sw360 = new StringBuilder();
        StringBuilder sw375 = new StringBuilder();
        StringBuilder sw384 = new StringBuilder();
        StringBuilder sw392 = new StringBuilder();
        StringBuilder sw400 = new StringBuilder();
        StringBuilder sw411 = new StringBuilder();
        StringBuilder sw480 = new StringBuilder();
        StringBuilder sw533 = new StringBuilder();
        StringBuilder sw600 = new StringBuilder();
        StringBuilder sw640 = new StringBuilder();
        StringBuilder sw720 = new StringBuilder();
        StringBuilder sw768 = new StringBuilder();
        StringBuilder sw800 = new StringBuilder();
        StringBuilder sw820 = new StringBuilder();

        try {
            System.out.println("生成不同分辨率：");

            reader = new BufferedReader(new FileReader(file));
            String tempString;
            int line = 1;

            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {

                if (tempString.contains("</dimen>")) {
                    String start = tempString.substring(0, tempString.indexOf(">") + 1);

                    String end = tempString.substring(tempString.lastIndexOf("<") - 2);
                    // 截取<dimen></dimen>标签内的内容，从>右括号开始，到左括号减2，取得配置的数字
                    Double num = Double.parseDouble
                            (tempString.substring(tempString.indexOf(">") + 1,
                                    tempString.indexOf("</dimen>") - 2));

                    sw240.append(start).append(num * 240 / defaultDp).append(end).append("\r\n");
                    sw320.append(start).append(num * 320 / defaultDp).append(end).append("\r\n");
                    sw360.append(start).append(num * 360 / defaultDp).append(end).append("\r\n");
                    sw375.append(start).append(num * 375 / defaultDp).append(end).append("\r\n");
                    sw384.append(start).append(num * 384 / defaultDp).append(end).append("\r\n");
                    sw392.append(start).append(num * 392 / defaultDp).append(end).append("\r\n");
                    sw400.append(start).append(num * 400 / defaultDp).append(end).append("\r\n");
                    sw411.append(start).append(num * 411 / defaultDp).append(end).append("\r\n");
                    sw480.append(start).append(num * 480 / defaultDp).append(end).append("\r\n");
                    sw533.append(start).append(num * 533 / defaultDp).append(end).append("\r\n");
                    sw600.append(start).append(num * 600 / defaultDp).append(end).append("\r\n");
                    sw640.append(start).append(num * 640 / defaultDp).append(end).append("\r\n");
                    sw720.append(start).append(num * 720 / defaultDp).append(end).append("\r\n");
                    sw768.append(start).append(num * 768 / defaultDp).append(end).append("\r\n");
                    sw800.append(start).append(num * 800 / defaultDp).append(end).append("\r\n");
                    sw820.append(start).append(num * 820 / defaultDp).append(end).append("\r\n");
                } else {
                    sw240.append(tempString).append("");
                    sw320.append(tempString).append("");
                    sw360.append(tempString).append("");
                    sw375.append(tempString).append("");
                    sw384.append(tempString).append("");
                    sw392.append(tempString).append("");
                    sw400.append(tempString).append("");
                    sw411.append(tempString).append("");
                    sw480.append(tempString).append("");
                    sw533.append(tempString).append("");
                    sw600.append(tempString).append("");
                    sw640.append(tempString).append("");
                    sw720.append(tempString).append("");
                    sw768.append(tempString).append("");
                    sw800.append(tempString).append("");
                    sw820.append(tempString).append("");
                }
                line++;
            }

            reader.close();
            System.out.println("<!--  sw240 -->");
            System.out.println(sw240);

            System.out.println("<!--  sw320 -->");
            System.out.println(sw320);

            System.out.println("<!--  sw360 -->");
            System.out.println(sw360);

            System.out.println("<!--  sw375 -->");
            System.out.println(sw375);

            System.out.println("<!--  SW384 -->");
            System.out.println(sw384);

            System.out.println("<!--  SW392 -->");
            System.out.println(sw392);

            System.out.println("<!--  sw400 -->");
            System.out.println(sw400);

            System.out.println("<!--  sw411 -->");
            System.out.println(sw411);

            System.out.println("<!--  sw480 -->");
            System.out.println(sw480);

            System.out.println("<!--  sw533 -->");
            System.out.println(sw533);

            System.out.println("<!--  sw600 -->");
            System.out.println(sw600);

            System.out.println("<!--  sw640 -->");
            System.out.println(sw640);

            System.out.println("<!--  sw720 -->");
            System.out.println(sw720);

            System.out.println("<!--  sw768 -->");
            System.out.println(sw768);

            System.out.println("<!--  sw800 -->");
            System.out.println(sw800);

            System.out.println("<!--  sw820 -->");
            System.out.println(sw820);

            // 指定文件夹路径，以及文件名及格式。
            File w240 = new File("./library-resource/src/main/res/values-w240dp/dimens.xml");
            File w320 = new File("./library-resource/src/main/res/values-w320dp/dimens.xml");
            File w360 = new File("./library-resource/src/main/res/values-w360dp/dimens.xml");
            File w375 = new File("./library-resource/src/main/res/values-w375dp/dimens.xml");
            File w384 = new File("./library-resource/src/main/res/values-w384dp/dimens.xml");
            File w392 = new File("./library-resource/src/main/res/values-w392dp/dimens.xml");
            File w400 = new File("./library-resource/src/main/res/values-w400dp/dimens.xml");
            File w411 = new File("./library-resource/src/main/res/values-w411dp/dimens.xml");
            File w480 = new File("./library-resource/src/main/res/values-w480dp/dimens.xml");
            File w533 = new File("./library-resource/src/main/res/values-w533dp/dimens.xml");
            File w600 = new File("./library-resource/src/main/res/values-w600dp/dimens.xml");
            File w640 = new File("./library-resource/src/main/res/values-w640dp/dimens.xml");
            File w720 = new File("./library-resource/src/main/res/values-w720dp/dimens.xml");
            File w768 = new File("./library-resource/src/main/res/values-w768dp/dimens.xml");
            File w800 = new File("./library-resource/src/main/res/values-w800dp/dimens.xml");
            File w820 = new File("./library-resource/src/main/res/values-w820dp/dimens.xml");

            Make(w240);
            Make(w320);
            Make(w360);
            Make(w375);
            Make(w384);
            Make(w392);
            Make(w400);
            Make(w411);
            Make(w480);
            Make(w533);
            Make(w600);
            Make(w640);
            Make(w720);
            Make(w768);
            Make(w800);
            Make(w820);

            // 将内容写入到指定的文件中
            writeFile(w240, sw240.toString());
            writeFile(w320, sw320.toString());
            writeFile(w360, sw360.toString());
            writeFile(w375, sw375.toString());
            writeFile(w384, sw384.toString());
            writeFile(w392, sw392.toString());
            writeFile(w400, sw400.toString());
            writeFile(w411, sw411.toString());
            writeFile(w480, sw480.toString());
            writeFile(w533, sw480.toString());
            writeFile(w600, sw600.toString());
            writeFile(w640, sw640.toString());
            writeFile(w720, sw720.toString());
            writeFile(w768, sw768.toString());
            writeFile(w800, sw800.toString());
            writeFile(w820, sw820.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

    /**
     * 写入文件
     * @param file 文件
     * @param text 内容
     */
    public static void writeFile(File file, String text) {
        PrintWriter out = null;
        try {
            out = new PrintWriter(new BufferedWriter(new FileWriter(file)));
            out.println(text);
        } catch (IOException e) {
            e.printStackTrace();
        }
        out.close();
    }

    /**
     * 自定义检测生成指定文件夹下的指定文件
     * @param file 文件
     */
    public static void Make(File file) {
        try {
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}