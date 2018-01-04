package com.fountain.tools;

import android.text.TextUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Creator : Fountain Tao<br/>
 * Creation Time : 2017/12/14<br/>
 * Description : MD5工具类<br/>
 */
public class MD5Utils {
    private volatile static MD5Utils mInstance;

    public static MD5Utils getInstance() {
        if (null == mInstance) {
            synchronized (MD5Utils.class) {
                if (null == mInstance) {
                    mInstance = new MD5Utils();
                }
            }
        }
        return mInstance;
    }

    private MD5Utils() {
    }

    // TODO: [record] 32位MD5加密方法

    /**
     * 32位MD5加密方法
     */
    public String getMd5Value(String value) {
        try {
            MessageDigest bmd5 = MessageDigest.getInstance("MD5");
            bmd5.update(value.getBytes());
            int i;
            StringBuffer buf = new StringBuffer();
            byte[] b = bmd5.digest();// 加密
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            return buf.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    // TODO: [record] 16位MD5加密方法

    /**
     * 16位MD5加密方法
     */
    public String getMd5Value16(String value) {
        String md5Value = getMd5Value(value);
        if (TextUtils.isEmpty(md5Value)) {
            return "";
        }
        return md5Value.substring(8, 24);
    }
}