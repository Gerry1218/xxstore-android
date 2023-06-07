package com.wanris.business.common.utils;

import android.util.Log;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5加密
 */

public class MD5Helper {
    private static char[] hexDigest =
            {
                    '0', '1', '2', '3', '4', '5', '6', '7',
                    '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'
            };

    public synchronized static String getMD5(String str) {
        Log.d("MD5   ", str);
        byte[] sources = str.getBytes();
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(sources);
            byte[] tmps = digest.digest();
            char[] strs = new char[16 * 2];
            int k = 0;
            for (int i = 0; i < 16; i++) {
                byte b = tmps[i];
                strs[k++] = hexDigest[b >>> 4 & 0xf];
                strs[k++] = hexDigest[b & 0xf];
            }
            str = new String(strs);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        Log.d("MD5   ", str);
        return str;
    }
}
