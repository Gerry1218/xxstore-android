package com.wanris.business.common.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;
import android.util.Base64;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;

/**
 * sharePreference 存储 工具类
 */

public class PreferenceHelper {
    private static PreferenceHelper mInstance = new PreferenceHelper();

    private Context mContext;
    private SharedPreferences mPref;

    private PreferenceHelper() {
    }

    public synchronized static PreferenceHelper getInstance() {
        return mInstance;
    }

    public void init(Context context) {
        if (mContext == null) {
            mContext = context;
        }
        if (mPref == null) {
            mPref = context.getSharedPreferences("data", 0);
        }
    }

    public void put(String key, String value) {
        if (mPref != null) {
            Editor editor = mPref.edit();
            editor.putString(key, value);
            editor.commit();
        }
    }

    public String getString(String key) {
        return mPref.getString(key, "");
    }

    public String getString(String key, String def) {
        return mPref.getString(key, def);
    }

    public void put(String key, int value) {
        Editor editor = mPref.edit();
        editor.putInt(key, value);
        editor.commit();
    }

    public int getInt(String key) {
        return mPref.getInt(key, 0);
    }

    public void put(String key, long value) {
        Editor editor = mPref.edit();
        editor.putLong(key, value);
        editor.commit();
    }

    public long getLong(String key) {
        return mPref.getLong(key, 0);
    }

    public void put(String key, boolean value) {
        Editor editor = mPref.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    public boolean getBoolean(String key) {
        return getBoolean(key, false);
    }

    public boolean getBoolean(String key, boolean defaultVal) {
        return mPref.getBoolean(key, defaultVal);
    }

    public boolean contains(String key) {
        return mPref.contains(key);
    }

    public void remove(String key) {
        Editor editor = mPref.edit();
        editor.remove(key);
        editor.commit();
    }

    public void clear() {
        Editor editor = mPref.edit();
        editor.clear();
        editor.commit();
    }

    /**
     * 保存object 类型的值到SharedPrefrences中
     *
     * @param key    保存时的key
     * @param object 保存时的Object对象
     */
    public void putPreferencesObj(String key, Object object) {
        if (null == object) {
            return;
        }
        // // 创建字节输出流
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = null;
        try {
            // 创建对象输出流，并封装字节流
            oos = new ObjectOutputStream(baos);
            // 将对象写入字节流
            oos.writeObject(object);
            // 将字节流编码成base64的字符窜
            String oAuth_Base64 = new String(Base64.encode(baos.toByteArray(), 0));
            put(key, oAuth_Base64);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != baos) {
                try {
                    baos.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            if (null != oos) {
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 获取保存在SharedPrefrences中的Object类型的值
     *
     * @param key 保存时的key
     * @return Object 返回的Object对象
     */
    public Object getPreferencesObj(String key) {
        Object obj = null;
        String productBase64 = getString(key, "");
        if (TextUtils.isEmpty(productBase64)) {
            return null;
        }
        try {
            // 读取字节
            byte[] base64 = Base64.decode(productBase64.getBytes(), 0);
            // 封装到字节流
            ByteArrayInputStream bais = new ByteArrayInputStream(base64);

            // 再次封装
            ObjectInputStream bis = new ObjectInputStream(bais);
            try {
                // 读取对象
                obj = bis.readObject();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            bis.close();
            bais.close();
        } catch (StreamCorruptedException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }


}
