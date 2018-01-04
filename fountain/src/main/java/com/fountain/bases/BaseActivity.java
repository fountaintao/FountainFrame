package com.fountain.bases;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Toast;

import com.fountain.manager.ActivityManager;
import com.fountain.manager.BroadCastManager;
import com.fountain.manager.StatusBarManager;

/**
 * Creator : Fountain Tao<br/>
 * Creation Time : 2018/1/3<br/>
 * Description : Activity基类<br/>
 */
public abstract class BaseActivity extends FragmentActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentView());
        addActivity();
    }

    @Override
    protected void onDestroy() {
        removeActivity();
        super.onDestroy();
    }

    protected abstract @LayoutRes
    int getContentView();

    // TODO: [record] 打开一个指定控件的焦点（可获取焦点、触摸获取等）

    /**
     * 打开一个指定控件的焦点（可获取焦点、触摸获取等）
     */
    public void openFocusable(View v) {
        v.setFocusable(true);
        v.setFocusableInTouchMode(true);
    }

    // TODO: [record] 关闭一个指定控件的焦点（可获取焦点、触摸获取等）

    /**
     * 关闭一个指定控件的焦点（可获取焦点、触摸获取等）
     */
    public void closeFocusable(View v) {
        v.setFocusable(false);
        v.setFocusableInTouchMode(false);
    }

    // TODO: [record] 取消下一焦点（即把下一焦点置为本身）

    /**
     * 取消下一焦点（即把下一焦点置为本身）
     */
    public void cancelNextFocus(View v, boolean left, boolean up, boolean right, boolean down) {
        if (left) {
            v.setNextFocusLeftId(v.getId());
        }
        if (up) {
            v.setNextFocusUpId(v.getId());
        }
        if (right) {
            v.setNextFocusRightId(v.getId());
        }
        if (down) {
            v.setNextFocusDownId(v.getId());
        }
    }

    // TODO: [record] 短时间显示提示

    /**
     * 短时间显示提示
     */
    public void showTooltip(Object text) {
        Toast.makeText(this, String.valueOf(text), Toast.LENGTH_SHORT).show();
    }

    // TODO: [record] 长时间显示提示

    /**
     * 长时间显示提示
     */
    public void showLongTooltip(Object text) {
        Toast.makeText(this, String.valueOf(text), Toast.LENGTH_LONG).show();
    }

    public Context getContext() {
        return this;
    }

    public Activity getActivity() {
        return this;
    }

    public StatusBarManager getStatusBarManager() {
        return StatusBarManager.getInstance();
    }

    public BroadCastManager getBroadCastManager() {
        return BroadCastManager.getInstance();
    }

    public ActivityManager getActivityManager() {
        return ActivityManager.getInstance();
    }

    public void addActivity() {
        getActivityManager().addActivity(this);
    }

    public void removeActivity() {
        getActivityManager().removeActivity(this);
    }
}