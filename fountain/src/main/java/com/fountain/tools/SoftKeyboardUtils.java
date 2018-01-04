package com.fountain.tools;

import android.content.Context;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.fountain.fountain.BaseApplication;

/**
 * Creator : Fountain Tao<br/>
 * Creation Time : 2018/1/4<br/>
 * Description : 软件盘工具类<br/>
 */
public class SoftKeyboardUtils {
    private Context mContext;

    private static class SingletonHolder {
        private volatile static SoftKeyboardUtils mInstance = new SoftKeyboardUtils();
    }

    public static SoftKeyboardUtils getInstance() {
        return SingletonHolder.mInstance;
    }

    private SoftKeyboardUtils() {
        mContext = BaseApplication.getInstance().getApplicationContext();
    }

    // TODO: [record] 获取InputMethodManager

    /**
     * 获取InputMethodManager
     */
    private InputMethodManager getInputMethodManager() {
        return (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
    }

    // TODO: [record] 打开软键盘

    /**
     * 打开软键盘
     */
    public void openSoftKeyboard(EditText mEditText) {
        InputMethodManager inputMethodManager = getInputMethodManager();
        inputMethodManager.showSoftInput(mEditText, InputMethodManager.RESULT_SHOWN);
        inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
    }

    // TODO: [record] 关闭软键盘

    /**
     * 关闭软键盘
     */
    public void closeSoftKeyboard(EditText mEditText) {
        InputMethodManager inputMethodManager = getInputMethodManager();
        inputMethodManager.hideSoftInputFromWindow(mEditText.getWindowToken(), 0);
    }
}