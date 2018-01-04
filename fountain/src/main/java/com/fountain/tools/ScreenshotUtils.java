package com.fountain.tools;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.view.View;

/**
 * Creator : Fountain Tao<br/>
 * Creation Time : 2018/1/4<br/>
 * Description : 截图工具类<br/>
 */
public class ScreenshotUtils {
    private static class SingletonHolder {
        private volatile static ScreenshotUtils mInstance = new ScreenshotUtils();
    }

    public static ScreenshotUtils getInstance() {
        return SingletonHolder.mInstance;
    }

    private ScreenshotUtils() {
    }

    // TODO: [record] 获取当前屏幕截图，包含状态栏

    /**
     * 获取当前屏幕截图，包含状态栏
     */
    public Bitmap getScreenshot(Activity activity) {
        return getViewScreenshot(activity.getWindow().getDecorView());
    }

    // TODO: [record] 获取当前屏幕截图，不包含状态栏

    /**
     * 获取当前屏幕截图，不包含状态栏
     */
    public Bitmap getScreenshotOutStatusBar(Activity activity) {
        Rect frame = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
        int statusBarHeight = frame.top,
                width = Utils.getInstance().getScreenWidth(),
                height = Utils.getInstance().getScreenHeight();
        return Bitmap.createBitmap(getScreenshot(activity), 0, statusBarHeight,
                width, height - statusBarHeight);
    }

    // TODO: [record] 获取当前View的截图

    /**
     * 获取当前View的截图
     */
    public Bitmap getViewScreenshot(View v) {
        v.setDrawingCacheEnabled(true);
        v.buildDrawingCache();
        Bitmap bmp = v.getDrawingCache();
        int width = v.getMeasuredWidth();
        int height = v.getMeasuredHeight();
        Bitmap bitmap = Bitmap.createBitmap(bmp, 0, 0, width, height);
        v.destroyDrawingCache();
        return bitmap;
    }
}