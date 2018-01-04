package com.fountain.fountain;

import android.support.multidex.MultiDexApplication;

/**
 * Creator : Fountain Tao<br/>
 * Creation Time : 2018/1/3<br/>
 * Description : Application基类<br/>
 */
public class BaseApplication extends MultiDexApplication {
    private volatile static BaseApplication mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }

    public static BaseApplication getInstance() {
        if (null == mInstance) {
            throw new RuntimeException("null pointer");
        }
        return mInstance;
    }
}