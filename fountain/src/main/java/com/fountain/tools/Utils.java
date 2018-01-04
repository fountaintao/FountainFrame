package com.fountain.tools;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.text.TextUtils;
import android.util.TypedValue;

import com.fountain.fountain.BaseApplication;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Creator : Fountain Tao<br/>
 * Creation Time : 2018/1/3<br/>
 * Description : 常用工具类<br/>
 */
public class Utils {
    private volatile static Utils mInstance;
    private Context mContext;

    public static Utils getInstance() {
        if (null == mInstance) {
            synchronized (Utils.class) {
                if (null == mInstance) {
                    mInstance = new Utils();
                }
            }
        }
        return mInstance;
    }

    private Utils() {
        mContext = BaseApplication.getInstance().getApplicationContext();
    }

    // TODO: [record] 获取当前屏幕宽度(px)

    /**
     * 获取当前屏幕宽度(px)
     */
    public int getScreenWidth() {
        return mContext.getResources().getDisplayMetrics().widthPixels;
    }

    // TODO: [record] 获取当前屏幕高度(含状态栏)(px)

    /**
     * 获取当前屏幕高度(含状态栏)(px)
     */
    public int getScreenHeight() {
        return mContext.getResources().getDisplayMetrics().heightPixels;
    }

    // TODO: [record] 获取屏幕的状态栏高度(px)

    /**
     * 获取屏幕的状态栏高度(px)
     */
    public int getScreenStatusBarHeight() {
        //获取status_bar_height资源的ID
        int resourceId = mContext.getResources().getIdentifier(
                "status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            //根据资源ID获取响应的尺寸值
            return mContext.getResources().getDimensionPixelSize(resourceId);
        }
        return 0;
    }

    /**
     * 获取屏幕的状态栏高度(px)
     */
    public int getScreenStatusBarHeight(Activity activity) {
        Rect frame = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
        int statusBarHeight = frame.top;
        if (0 == statusBarHeight) {
            statusBarHeight = getScreenStatusBarHeight();
        }
        return statusBarHeight;
    }

    // TODO: [record] dp转px

    /**
     * dp转px
     */
    public int dp2px(float dpVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dpVal, mContext.getResources().getDisplayMetrics());
    }

    // TODO: [record] sp转px

    /**
     * sp转px
     */
    public int sp2px(float spVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                spVal, mContext.getResources().getDisplayMetrics());
    }

    // TODO: [record] px转dp
    public float px2dp(float pxVal) {
        return (pxVal / mContext.getResources().getDisplayMetrics().density);
    }

    // TODO: [record] px转sp

    /**
     * px转sp
     */
    public float px2sp(float pxVal) {
        return (pxVal / mContext.getResources().getDisplayMetrics().scaledDensity);
    }

    // TODO: [record] 邮箱格式是否正确

    /**
     * 邮箱格式是否正确
     */
    public boolean isEmail(String email) {
        if (TextUtils.isEmpty(email)) {
            return false;
        }
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}