package com.fountain.manager;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Creator : Fountain Tao<br/>
 * Creation Time : 2018/1/3<br/>
 * Description : Activity管理器<br/>
 */
public class ActivityManager {
    private volatile static ActivityManager mInstance;
    /**
     * 储存Activity的集合
     */
    private List<Activity> aList;

    public static ActivityManager getInstance() {
        if (null == mInstance) {
            synchronized (ActivityManager.class) {
                if (null == mInstance) {
                    mInstance = new ActivityManager();
                }
            }
        }
        return mInstance;
    }

    private ActivityManager() {
        aList = new ArrayList<>();
    }

    // TODO: [record] 存在当前Activity

    /**
     * 存在当前Activity
     */
    public boolean isExist(Activity activity) {
        return aList.contains(activity);
    }

    // TODO: [record] 添加当前Activity

    /**
     * 添加当前Activity
     */
    public void addActivity(Activity activity) {
        if (!aList.contains(activity)) {
            aList.add(activity);
        }
    }

    // TODO: [record] 销毁当前Activity

    /**
     * 销毁当前Activity
     */
    public void removeActivity(Activity activity) {
        if (aList.isEmpty()) {
            return;
        }
        if (aList.contains(activity)) {
            aList.remove(activity);//把当前Activity从集合中移除
            if (!activity.isFinishing()) {//当前Activity不在销毁过程中
                activity.finish();//销毁当前Activity
            }
        }
    }

    // TODO: [record] 清空所有Activity

    /**
     * 清空所有Activity
     */
    public void clearActivity() {
        if (aList.isEmpty()) {
            return;
        }
        for (Activity activity : aList) {
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
        aList.clear();
    }

    // TODO: [record] 保留当前Activity & 清空其他Activity

    /**
     * 保留当前Activity & 清空其他Activity
     */
    public void keepActivity(Activity a) {
        if (aList.isEmpty()) {
            return;
        }
        for (Activity activity : aList) {
            if (!activity.isFinishing()) {
                if (a != activity) {
                    activity.finish();
                }
            }
        }
        aList.clear();
        aList.add(a);
    }

    // TODO: [record] 保留指定的首个Class<? extends Activity> & 清空其他Activity

    /**
     * 保留指定的首个Class<? extends Activity> & 清空其他Activity
     */
    public void keepActivity(Class<? extends Activity> cls) {
        if (aList.isEmpty()) {
            return;
        }
        Activity a = null;
        for (Activity activity : aList) {
            if (null == a &&
                    activity.getClass().equals(cls)) {
                a = activity;
            } else {
                if (!activity.isFinishing()) {
                    activity.finish();
                }
            }
        }
        aList.clear();
        if (null != a) {
            aList.add(a);
        }
    }

    // TODO: [record] 回退指定数量的Activity栈

    /**
     * 回退指定数量的Activity栈
     */
    public void rollbackActivity(int num) {
        if (num <= 0) {
            return;
        }
        int count = null == aList ? 0 : aList.size();
        if (num > count) {
            clearActivity();
            return;
        }
        for (int i = 0; i < num; i++) {
            Activity activity = aList.get((count - 1) - i);
            aList.remove(activity);
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
    }
}