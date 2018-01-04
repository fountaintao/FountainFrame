package com.fountain.tools;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.fountain.fountain.BaseApplication;

/**
 * Creator : Fountain Tao<br/>
 * Creation Time : 2018/1/4<br/>
 * Description : 获取程序信息工具类<br/>
 */
public class AppUtils {
    private Context mContext;

    private static class SingletonHolder {
        private volatile static AppUtils mInstance = new AppUtils();
    }

    public static AppUtils getInstance() {
        return SingletonHolder.mInstance;
    }

    private AppUtils() {
        mContext = BaseApplication.getInstance().getApplicationContext();
    }

    // TODO: [record] 获取PackageInfo

    /**
     * 获取PackageInfo
     */
    public PackageInfo getPackageInfo() {
        try {
            PackageManager packageManager = mContext.getPackageManager();
            return packageManager.getPackageInfo(
                    mContext.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    // TODO: [record] 获取应用程序名称

    /**
     * 获取应用程序名称
     */
    public String getAppName() {
        PackageInfo packageInfo = getPackageInfo();
        return null == packageInfo ? null : mContext.getResources().getString(
                packageInfo.applicationInfo.labelRes);
    }

    // TODO: [record] 获取应用程序版本名称信息

    /**
     * 获取应用程序版本名称信息
     */
    public String getVersionName() {
        PackageInfo packageInfo = getPackageInfo();
        return null == packageInfo ? null : packageInfo.versionName;
    }

    // TODO: [record] 获取应用程序版本名称信息

    /**
     * 获取应用程序版本名称信息
     */
    public int getVersionCode() {
        PackageInfo packageInfo = getPackageInfo();
        return null == packageInfo ? -1 : packageInfo.versionCode;
    }
}