package com.fountain.tools;

import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.v7.content.res.AppCompatResources;
import android.transition.Transition;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;

/**
 * Creator : Fountain Tao<br/>
 * Creation Time : 2017/12/14<br/>
 * Description : PopupWindow工具类<br/>
 */
public class PopUtils {
    private PopupWindow popupWindow;

    private static class SingletonHolder {
        private volatile static PopUtils mInstance = new PopUtils();
    }

    public static PopUtils getInstance() {
        return SingletonHolder.mInstance;
    }

    private PopUtils() {
        createPopup();
    }

    // TODO: [record] 创建PopupWindow
    private void createPopup() {
        if (null == popupWindow) {
            popupWindow = new PopupWindow();
        }
    }

    /**
     * 设置 popupWindow 的显示View
     *
     * @param contentView View
     */
    public PopUtils setContentView(View contentView) {
        createPopup();
        popupWindow.setContentView(contentView);
        return getInstance();
    }

    /**
     * 设置 popupWindow 的可视宽度
     *
     * @param width 宽度
     */
    public PopUtils setWidth(int width) {
        popupWindow.setWidth(width);
        return getInstance();
    }

    /**
     * 设置 popupWindow 的可视高度
     *
     * @param height 高度
     */
    public PopUtils setHeight(int height) {
        popupWindow.setHeight(height);
        return getInstance();
    }

    /**
     * 设置 popupWindow 的可视大小
     *
     * @param width  宽度
     * @param height 高度
     */
    public PopUtils setSize(int width, int height) {
        popupWindow.setWidth(width);
        popupWindow.setHeight(height);
        return getInstance();
    }

    /**
     * 设置 popupWindow 的弹出窗体是否可点击
     *
     * @param focusable true -- 可点
     */
    public PopUtils setFocusable(boolean focusable) {
        popupWindow.setFocusable(focusable);
        return getInstance();
    }

    /**
     * 设置 popupWindow 的背景，做到点击外部区域，popupWindow 消失
     *
     * @param background 背景（Drawable）
     */
    public PopUtils setBackgroundDrawable(Drawable background) {
        popupWindow.setBackgroundDrawable(background);
        return getInstance();
    }

    public PopUtils setBackgroundColor(@ColorInt int color) {
        ColorDrawable drawable = new ColorDrawable(color);
        return setBackgroundDrawable(drawable);
    }

    public PopUtils setBackgroundResources(@DrawableRes int resId) {
        createPopup();
        if (null != popupWindow.getContentView()) {
            Drawable drawable = AppCompatResources.getDrawable(popupWindow.getContentView().getContext(), resId);
            popupWindow.setBackgroundDrawable(drawable);
        }
        return getInstance();
    }

    /**
     * 控制弹出窗口是否会通知窗口外的触摸事件
     */
    public PopUtils setOutsideTouchable(boolean touchable) {
        popupWindow.setOutsideTouchable(touchable);
        return getInstance();
    }

    /**
     * 更改此弹出窗口的动画样式资源
     */
    public PopUtils setAnimationStyle(int animationStyle) {
        popupWindow.setAnimationStyle(animationStyle);
        return getInstance();
    }

    /**
     * 将弹出窗口附加到父窗口的装饰框，以避免与导航栏等屏幕装饰重叠
     */
    public PopUtils setAttachedInDecor(boolean enabled) {
        createPopup();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
            popupWindow.setAttachedInDecor(enabled);
        }
        return getInstance();
    }

    /**
     * 允许弹出窗口延伸到屏幕的边界之外
     */
    public PopUtils setClippingEnabled(boolean enabled) {
        popupWindow.setClippingEnabled(enabled);
        return getInstance();
    }

    /**
     * 指定此弹出窗口的高
     */
    public PopUtils setElevation(float elevation) {
        createPopup();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            popupWindow.setElevation(elevation);
        }
        return getInstance();
    }

    /**
     * 在弹出的窗口中设置标志来忽略面颊新闻事件;默认情况下，这个标志被设置为false，这意味着弹出窗口不会忽略脸颊新闻发送事件
     */
    public PopUtils setIgnoreCheekPress() {
        popupWindow.setIgnoreCheekPress();
        return getInstance();
    }

    /**
     * 控制如何的弹出与输入方法工作
     *
     * @param mode {@link PopupWindow#INPUT_METHOD_FROM_FOCUSABLE}
     *             -对输入法的要求应该基于弹出框的可聚焦性。
     *             这就是说，如果它是可重点的，而不是需要使用输入法，否则它不会<br/>
     *             {@link PopupWindow#INPUT_METHOD_NEEDED}
     *             -这个弹出窗口总是需要使用输入法，而不管它是否可以调焦。
     *             这意味着它将始终显示，以便用户在显示时也可以操作输入法<br/>
     *             {@link PopupWindow#INPUT_METHOD_NOT_NEEDED}
     *             -这个弹出窗口不需要使用输入法，不管它是否可以调焦。
     *             这意味着它将始终显示为根据需要在屏幕上使用尽可能多的空间，而不管是否覆盖了输入法<br/>
     */
    public PopUtils setInputMethodMode(int mode) {
        popupWindow.setInputMethodMode(mode);
        return getInstance();
    }

    /**
     * 设置当显示为下拉时，弹出窗口是否应该与它的锚视图重叠
     */
    public PopUtils setOverlapAnchor(boolean overlapAnchor) {
        createPopup();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            popupWindow.setOverlapAnchor(overlapAnchor);
        }
        return getInstance();
    }

    /**
     * 设置软输入区域的操作模式
     *
     * @param mode <br/>
     *             {@link WindowManager.LayoutParams#SOFT_INPUT_STATE_UNSPECIFIED}
     *             -没有指定状态<br/>
     *             {@link WindowManager.LayoutParams#SOFT_INPUT_STATE_UNCHANGED}
     *             -请不要改变软输入区域的状态<br/>
     *             {@link WindowManager.LayoutParams#SOFT_INPUT_STATE_HIDDEN}
     *             -在正常情况下（当用户向前导航到窗口时），请隐藏任何软输入区域<br/>
     *             {@link WindowManager.LayoutParams#SOFT_INPUT_STATE_ALWAYS_VISIBLE}
     *             -当此窗口收到输入焦点时，请始终使软输入区域可见<br/>
     *             {@link WindowManager.LayoutParams#SOFT_INPUT_STATE_VISIBLE}
     *             -请在正常情况下（当用户向前导航到窗口时）显示软输入区域<br/>
     *             {@link WindowManager.LayoutParams#SOFT_INPUT_STATE_ALWAYS_HIDDEN}
     *             -当此窗口获得焦点时，请始终隐藏任何软输入区域<br/>
     *             {@link WindowManager.LayoutParams#SOFT_INPUT_MASK_STATE}
     *             -确定该窗口的软输入区域的期望可见性状态的位掩码<br/>
     *             {@link WindowManager.LayoutParams#SOFT_INPUT_MASK_ADJUST}
     *             -确定窗口应该被调整以容纳软输入窗口的方式的掩码<br/>
     *             {@link WindowManager.LayoutParams#SOFT_INPUT_IS_FORWARD_NAVIGATION}
     *             -当用户向前导航到窗口时设置。这通常由系统为您自动设置，但您可能需要在某些情况下在您自己显示窗口时进行设置。
     *             窗口显示后，该标志将自动清除<br/>
     *             {@link WindowManager.LayoutParams#SOFT_INPUT_ADJUST_UNSPECIFIED}
     *             -没有指定。系统将根据窗口的内容尝试选择一个或另一个<br/>
     *             {@link WindowManager.LayoutParams#SOFT_INPUT_ADJUST_RESIZE}
     *             -设置为允许在显示输入法时调整窗口的大小，使其内容不被输入法覆盖。
     *             这不能与 SOFT_INPUT_ADJUST_PAN; 如果这些都没有设置，那么系统会根据窗口的内容尝试选择一个或另一个。
     *             如果窗口的布局参数标志包含FLAG_FULLSCREEN，则此值softInputMode将被忽略; 窗口不会调整大小，但会保持全屏<br/>
     *             {@link WindowManager.LayoutParams#SOFT_INPUT_ADJUST_PAN}
     *             -设置为在显示输入法时有一个窗口平移，所以它不需要处理调整大小，而是由框架平移，以确保当前输入焦点可见。
     *             这不能与SOFT_INPUT_ADJUST_RESIZE; 如果这些都没有设置，那么系统会根据窗口的内容尝试选择一个或另一个<br/>
     *             {@link WindowManager.LayoutParams#SOFT_INPUT_ADJUST_NOTHING}
     *             -设置为不显示输入法调整窗口。该窗口不会被调整大小，也不会被平移以使其焦点可见<br/>
     */
    public PopUtils setSoftInputMode(int mode) {
        popupWindow.setSoftInputMode(mode);
        return getInstance();
    }

    /**
     * 允许弹出窗口在其他窗口中分割触摸，也支持分割触摸
     */
    public PopUtils setSplitTouchEnabled(boolean enabled) {
        popupWindow.setSplitTouchEnabled(enabled);
        return getInstance();
    }

    /**
     * 为所有被发送到弹出窗口的触摸事件设置一个回调
     */
    public PopUtils setTouchInterceptor(View.OnTouchListener l) {
        popupWindow.setTouchInterceptor(l);
        return getInstance();
    }

    /**
     * 改变弹出窗口的可触摸性
     */
    public PopUtils setTouchable(boolean touchable) {
        popupWindow.setTouchable(touchable);
        return getInstance();
    }

    /**
     * 设置此窗口的布局类型
     *
     * @param layoutType {@link WindowManager.LayoutParams#TYPE_BASE_APPLICATION}<br/>
     *                   {@link WindowManager.LayoutParams#TYPE_APPLICATION}<br/>
     *                   {@link WindowManager.LayoutParams#TYPE_APPLICATION_STARTING}<br/>
     *                   {@link WindowManager.LayoutParams#TYPE_APPLICATION_PANEL}<br/>
     *                   {@link WindowManager.LayoutParams#TYPE_APPLICATION_MEDIA}<br/>
     *                   {@link WindowManager.LayoutParams#TYPE_APPLICATION_SUB_PANEL}<br/>
     *                   {@link WindowManager.LayoutParams#TYPE_APPLICATION_ATTACHED_DIALOG}<br/>
     *                   {@link WindowManager.LayoutParams#TYPE_STATUS_BAR}<br/>
     *                   {@link WindowManager.LayoutParams#TYPE_SEARCH_BAR}<br/>
     *                   {@link WindowManager.LayoutParams#TYPE_PHONE}<br/>
     *                   {@link WindowManager.LayoutParams#TYPE_SYSTEM_ALERT}<br/>
     *                   {@link WindowManager.LayoutParams#TYPE_TOAST}<br/>
     *                   {@link WindowManager.LayoutParams#TYPE_SYSTEM_OVERLAY}<br/>
     *                   {@link WindowManager.LayoutParams#TYPE_PRIORITY_PHONE}<br/>
     *                   {@link WindowManager.LayoutParams#TYPE_STATUS_BAR_PANEL}<br/>
     *                   {@link WindowManager.LayoutParams#TYPE_SYSTEM_DIALOG}<br/>
     *                   {@link WindowManager.LayoutParams#TYPE_KEYGUARD_DIALOG}<br/>
     *                   {@link WindowManager.LayoutParams#TYPE_SYSTEM_ERROR}<br/>
     *                   {@link WindowManager.LayoutParams#TYPE_INPUT_METHOD}<br/>
     *                   {@link WindowManager.LayoutParams#TYPE_INPUT_METHOD_DIALOG}<br/>
     */
    public PopUtils setWindowLayoutType(int layoutType) {
        createPopup();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            popupWindow.setWindowLayoutType(layoutType);
        }
        return getInstance();
    }

    /**
     * 设置 进入 的过度模式
     *
     * @param enterTransition 看{@link Transition}
     */
    public PopUtils setEnterTransition(Transition enterTransition) {
        createPopup();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            popupWindow.setEnterTransition(enterTransition);
        }
        return getInstance();
    }

    /**
     * 设置 退出 的过度模式
     *
     * @param exitTransition 看{@link Transition}
     */
    public PopUtils setExitTransition(Transition exitTransition) {
        createPopup();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            popupWindow.setExitTransition(exitTransition);
        }
        return getInstance();
    }

    /**
     * 设置 popupWindow 的销毁监听
     *
     * @param onDismissListener 监听
     */
    public PopUtils setOnDismissListener(PopupWindow.OnDismissListener onDismissListener) {
        popupWindow.setOnDismissListener(onDismissListener);
        return getInstance();
    }

    /**
     * 将 popupWindow 显示在指定 View 下方
     *
     * @param anchor View
     */
    public void showAsDropDown(View anchor) {
        popupWindow.showAsDropDown(anchor);
    }

    /**
     * 将 popupWindow 显示在指定 View 下方，并偏移
     *
     * @param anchor View
     * @param xoff   X 的偏移量
     * @param yoff   Y 的偏移量
     */
    public void showAsDropDown(View anchor, int xoff, int yoff) {
        popupWindow.showAsDropDown(anchor, xoff, yoff);
    }

    /**
     * 将 popupWindow 显示在指定 View 的指定方位，并偏移
     *
     * @param anchor  View
     * @param xoff    X 的偏移量
     * @param yoff    Y 的偏移量
     * @param gravity 方位（上下左右 {@link Gravity#TOP}、{@link Gravity#BOTTOM}...）
     */
    public void showAsDropDown(View anchor, int xoff, int yoff, int gravity) {
        popupWindow.showAsDropDown(anchor, xoff, yoff, gravity);
    }

    /**
     * 将 popupWindow 显示在指定的父视图中,并偏移
     *
     * @param parent  View
     * @param gravity 方位（上下左右 {@link Gravity#TOP}、{@link Gravity#BOTTOM}...）
     * @param x       X 的偏移量
     * @param y       Y 的偏移量
     */
    public void showAtLocation(View parent, int gravity, int x, int y) {
        popupWindow.showAtLocation(parent, gravity, x, y);
    }

    public void showAtLocation(View parent, int x, int y) {
        popupWindow.showAtLocation(parent, Gravity.NO_GRAVITY, x, y);
    }

    /**
     * 这个弹出窗口是否显示在屏幕上
     */
    public boolean isShowing() {
        if (null == popupWindow) {
            return false;
        }
        return popupWindow.isShowing();
    }

    /**
     * 销毁这个 popupWindow
     */
    public void dismiss() {
        if (null != popupWindow) {
            popupWindow.setOnDismissListener(null);
            popupWindow.dismiss();
            popupWindow = null;
        }
    }

    public void update() {
        popupWindow.update();
    }

    public void update(int width, int height) {
        popupWindow.update(width, height);
    }

    public void update(int x, int y, int width, int height) {
        popupWindow.update(x, y, width, height);
    }

    public void update(int x, int y, int width, int height, boolean force) {
        popupWindow.update(x, y, width, height, force);
    }

    public void update(View anchor, int width, int height) {
        popupWindow.update(anchor, width, height);
    }

    public void update(View anchor, int xoff, int yoff, int width, int height) {
        popupWindow.update(anchor, xoff, yoff, width, height);
    }
}