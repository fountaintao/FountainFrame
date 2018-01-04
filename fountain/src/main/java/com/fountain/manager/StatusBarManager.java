package com.fountain.manager;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.fountain.tools.Utils;

/**
 * Creator : Fountain Tao<br/>
 * Creation Time : 2018/1/3<br/>
 * Description : StatusBar管理器<br/>
 */
public class StatusBarManager {
    private volatile static StatusBarManager mInstance;
    private static final String STATUS_BAR_TAG = "StatusBar_TaoYong";
    private View layout;

    public static StatusBarManager getInstance() {
        if (null == mInstance) {
            synchronized (StatusBarManager.class) {
                if (null == mInstance) {
                    mInstance = new StatusBarManager();
                }
            }
        }
        return mInstance;
    }

    private StatusBarManager() {
    }

    /**
     * 清除状态栏系统自带标记
     */
    public void clearStatusBarFlags(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //Android api 为21及其以上，需要设置及清除的标记
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            activity.getWindow().setStatusBarColor(Color.TRANSPARENT);
        } else {
            //Android api 为21以下，需要设置的标记
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }

    /**
     * 有能力执行下一步
     */
    private boolean isPower(Activity activity) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
            //如果当前Android版本小于4.4，则返回
            return false;
        }
        ViewGroup androidContent = activity.findViewById(Window.ID_ANDROID_CONTENT);
        if (androidContent.getChildCount() <= 0) {
            //如果内容中没有孩子，则返回
            return false;
        }
        layout = androidContent.getChildAt(0);
        if (null == layout) {
            //如果第一个孩子为空，则返回
            return false;
        }
        return true;
    }

    /**
     * 设置状态栏一体化
     */
    public void setStatusBarIntegration(Activity activity) {
        if (isPower(activity)) {
            clearStatusBarFlags(activity);
            if (layout instanceof ViewGroup) {
                ViewGroup group = (ViewGroup) layout;
                if (group.getChildCount() <= 0) {
                    group.setFitsSystemWindows(true);
                    return;
                } else {
                    if (group.getFitsSystemWindows()) {
                        group.setFitsSystemWindows(false);
                    }
                    group.getChildAt(0).setFitsSystemWindows(true);
                }
            }
        }
    }

    /**
     * 设置状态栏颜色
     */
    public void setStatusBarColor(Activity activity, @ColorInt int color) {
        if (isPower(activity)) {
            clearStatusBarFlags(activity);
            layout.setFitsSystemWindows(true);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                activity.getWindow().setStatusBarColor(color);
            } else {
                ViewGroup decorView = (ViewGroup) activity.getWindow().getDecorView();
                View statusBar = decorView.findViewWithTag(STATUS_BAR_TAG);
                if (null == statusBar) {
                    statusBar = new View(activity);
                    statusBar.setLayoutParams(new ViewGroup.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT, Utils.getInstance().getScreenStatusBarHeight(activity)));
                    statusBar.setTag("StatusBar_TaoYong");
                    decorView.addView(statusBar);
                }
                statusBar.setBackgroundColor(color);
            }
        }
    }

    /**
     * 设置状态栏颜色资源
     */
    public void setStatusBarResources(Activity activity, @ColorRes int resId) {
        int color = activity.getResources().getColor(resId);
        setStatusBarColor(activity, color);
    }
}