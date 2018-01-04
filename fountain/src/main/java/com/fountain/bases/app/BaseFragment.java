package com.fountain.bases.app;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

/**
 * Creator : Fountain Tao<br/>
 * Creation Time : 2018/1/3<br/>
 * Description : app包下的Fragment基类<br/>
 */
public abstract class BaseFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(getContentView(), null);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        onCreateFragment(savedInstanceState);
    }

    protected abstract void onCreateFragment(@Nullable Bundle savedInstanceState);

    protected abstract @LayoutRes
    int getContentView();

    // TODO: [record] 根据ResId查找View

    /**
     * 根据ResId查找View
     */
    public <T extends View> T findViewById(@IdRes int resId) {
        if (null == getView()) {
            return null;
        }
        return getView().findViewById(resId);
    }

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
        Toast.makeText(getContext(), String.valueOf(text), Toast.LENGTH_SHORT).show();
    }

    // TODO: [record] 长时间显示提示

    /**
     * 长时间显示提示
     */
    public void showLongTooltip(Object text) {
        Toast.makeText(getContext(), String.valueOf(text), Toast.LENGTH_LONG).show();
    }

    public Context getContext() {
        return getActivity();
    }
}