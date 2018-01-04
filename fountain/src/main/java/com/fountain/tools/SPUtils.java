package com.fountain.tools;

import android.content.Context;
import android.content.SharedPreferences;

import com.fountain.fountain.BaseApplication;

import java.util.Map;
import java.util.Set;

/**
 * Creator : Fountain Tao<br/>
 * Creation Time : 2017/12/14<br/>
 * Description : SharedPreferences工具类<br/>
 */
public class SPUtils {
    private String SP_NAME = "paul_tao";//xml文件名
    private SharedPreferences sharedPreferences = null;//xml文件
    private SharedPreferences.Editor editor = null;//xml内容

    private static class SingletonHolder {
        private volatile static SPUtils mInstance = new SPUtils();
    }

    public static SPUtils getInstance() {
        return SingletonHolder.mInstance;
    }

    private SPUtils() {
        // TODO: [record] SharedPreferences储存机制
        Context context = BaseApplication.getInstance().getApplicationContext();
        if (sharedPreferences == null) {
            // TODO: [record] 获取SharedPreferences储存机制，若无则建立
            sharedPreferences = context.getSharedPreferences(SP_NAME, 0);
        }
        if (editor == null) {
            editor = sharedPreferences.edit();
        }
    }

    // TODO: [record] 提交键值对存入SharedPreferences储存机制
    public SPUtils put(String key, Object value) {
        if (null != editor) {
            if (value instanceof Integer) {
                editor.putInt(key, (Integer) value);
            } else if (value instanceof Boolean) {
                editor.putBoolean(key, (Boolean) value);
            } else if (value instanceof Float) {
                editor.putFloat(key, (Float) value);
            } else if (value instanceof Long) {
                editor.putLong(key, (Long) value);
            } else if (value instanceof Set) {
                editor.putStringSet(key, (Set<String>) value);
            } else {//若不能识别或者为String类型全存为String类型
                editor.putString(key, (String) value);
            }
            editor.apply();
        }
        return getInstance();
    }

    // TODO: [record] 从SharedPreferences储存机制中取出String值
    public String getString(String key) {
        if (null != sharedPreferences) {
            return sharedPreferences.getString(key, "");
        }
        return null;
    }

    // TODO: [record] 从SharedPreferences储存机制中取出int值
    public int getInt(String key) {
        if (null != sharedPreferences) {
            return sharedPreferences.getInt(key, -1);
        }
        return -1;
    }

    // TODO: [record] 从SharedPreferences储存机制中取出int值
    public boolean getBoolean(String key) {
        if (null != sharedPreferences) {
            return sharedPreferences.getBoolean(key, false);
        }
        return false;
    }

    // TODO: [record] 从SharedPreferences储存机制中取出int值
    public float getFloat(String key) {
        if (null != sharedPreferences) {
            return sharedPreferences.getFloat(key, -1f);
        }
        return -1f;
    }

    // TODO: [record] 从SharedPreferences储存机制中取出int值
    public long getLong(String key) {
        if (null != sharedPreferences) {
            return sharedPreferences.getLong(key, -1);
        }
        return -1;
    }

    // TODO: [record] 从SharedPreferences储存机制中取出Set<String>值
    public Set<String> getStringSet(String key) {
        if (null != sharedPreferences) {
            return sharedPreferences.getStringSet(key, null);
        }
        return null;
    }

    // TODO: [record] 从SharedPreferences储存机制中移除 key
    public SPUtils remove(String key) {
        if (null != editor) {
            editor.remove(key);
        }
        return getInstance();
    }

    // TODO: [record] 查询SharedPreferences储存机制中存在 key
    public boolean contains(String key) {
        if (null != sharedPreferences) {
            return sharedPreferences.contains(key);
        }
        return false;
    }

    // TODO: [record] 返回SharedPreferences储存机制中所有的键值对
    public Map<String, ?> getAll() {
        if (null != sharedPreferences) {
            return sharedPreferences.getAll();
        }
        return null;
    }

    // TODO: [record] 清除SharedPreferences储存机制中储存的所有值
    public void clear() {
        if (null != editor) {
            editor.clear().commit();
        }
    }
}