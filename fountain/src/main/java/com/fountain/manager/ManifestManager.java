package com.fountain.manager;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;

/**
 * Creator : Fountain Tao<br/>
 * Creation Time : 2017/12/8<br/>
 * Description : Manifest管理器<br/>
 */
public class ManifestManager {
    private volatile static ManifestManager mInstance;
    public static final int PERMISSION_REQUEST_CODE = 999;

    public static ManifestManager getInstance() {
        if (null == mInstance) {
            synchronized (ManifestManager.class) {
                if (null == mInstance) {
                    mInstance = new ManifestManager();
                }
            }
        }
        return mInstance;
    }

    private ManifestManager() {
    }

    // TODO: [record] 需要动态注册权限

    /**
     * 需要动态注册权限
     */
    public boolean isDynamicRegistration() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return true;
        }
        return false;
    }

    // TODO: [record] 权限数组已经全部存在(String[])

    /**
     * 权限数组已经全部存在(String[])
     */
    public boolean checkSelfPermission(Activity activity, String[] permissions) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int count = null == permissions ? 0 : permissions.length;
            for (int i = 0; i < count; i++) {
                int checkSelfPermission = activity.checkSelfPermission(permissions[i]);
                if (checkSelfPermission != PackageManager.PERMISSION_GRANTED)
                    return false;
            }
            return true;
        }
        return false;
    }

    // TODO: [record] 权限数组已经全部存在(int[])

    /**
     * 权限数组已经全部存在(int[])
     */
    public boolean checkSelfPermission(int[] grantResults) {
        int count = null == grantResults ? 0 : grantResults.length;
        for (int i = 0; i < count; i++) {
            if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    // TODO: [record] 动态注册权限

    /**
     * 动态注册权限
     */
    public void dynamicRegistrationAuthority(Activity activity, String[] permissions) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int count = null == permissions ? 0 : permissions.length;
            if (count < 1) {
                return;
            }
            activity.requestPermissions(permissions, PERMISSION_REQUEST_CODE);
        }
    }
}