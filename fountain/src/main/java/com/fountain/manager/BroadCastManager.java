package com.fountain.manager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Parcelable;

import com.fountain.fountain.BaseApplication;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Creator : Fountain Tao<br/>
 * Creation Time : 2017/12/6<br/>
 * Description : Broadcast管理器<br/>
 */
public class BroadCastManager {
    public static final String DATA = "BROADCAST_MANAGER";
    private volatile static BroadCastManager mInstance;
    private Map<String, Map<String, BroadcastReceiver>> receiverMap;
    private Context mContext;

    public static BroadCastManager getInstance() {
        if (null == mInstance) {
            synchronized (BroadCastManager.class) {
                if (null == mInstance) {
                    mInstance = new BroadCastManager();
                }
            }
        }
        return mInstance;
    }

    private BroadCastManager() {
        mContext = BaseApplication.getInstance().getApplicationContext();
        receiverMap = new LinkedHashMap<>();
    }

    // TODO: [record] 为指定action发送动态广播消息

    /**
     * 为指定action发送动态广播消息
     */
    public void sendBroadcast(String action, Object obj) {
        Intent intent = new Intent();
        intent.setAction(action);
        if (obj instanceof Integer) {
            intent.putExtra(DATA, (Integer) obj);
        } else if (obj instanceof Float) {
            intent.putExtra(DATA, (Float) obj);
        } else if (obj instanceof Double) {
            intent.putExtra(DATA, (Double) obj);
        } else if (obj instanceof Character) {
            intent.putExtra(DATA, (Character) obj);
        } else if (obj instanceof Byte) {
            intent.putExtra(DATA, (Byte) obj);
        } else if (obj instanceof Boolean) {
            intent.putExtra(DATA, (Boolean) obj);
        } else if (obj instanceof String) {
            intent.putExtra(DATA, (String) obj);
        } else if (obj instanceof Long) {
            intent.putExtra(DATA, (Long) obj);
        } else if (obj instanceof Short) {
            intent.putExtra(DATA, (Short) obj);
        } else if (obj instanceof Serializable) {
            intent.putExtra(DATA, (Serializable) obj);
        } else if (obj instanceof Parcelable) {
            intent.putExtra(DATA, (Parcelable) obj);
        } else if (obj instanceof Bundle) {
            intent.putExtra(DATA, (Bundle) obj);
        } else if (obj instanceof Parcelable[]) {
            intent.putExtra(DATA, (Parcelable[]) obj);
        } else if (obj instanceof boolean[]) {
            intent.putExtra(DATA, (boolean[]) obj);
        } else if (obj instanceof byte[]) {
            intent.putExtra(DATA, (byte[]) obj);
        } else if (obj instanceof char[]) {
            intent.putExtra(DATA, (char[]) obj);
        } else if (obj instanceof double[]) {
            intent.putExtra(DATA, (double[]) obj);
        } else if (obj instanceof float[]) {
            intent.putExtra(DATA, (float[]) obj);
        } else if (obj instanceof int[]) {
            intent.putExtra(DATA, (int[]) obj);
        } else if (obj instanceof String[]) {
            intent.putExtra(DATA, (String[]) obj);
        } else if (obj instanceof long[]) {
            intent.putExtra(DATA, (long[]) obj);
        } else if (obj instanceof short[]) {
            intent.putExtra(DATA, (short[]) obj);
        } else {
            intent.putExtra(DATA, String.valueOf(obj));
        }
        mContext.sendBroadcast(intent);
    }

    // TODO: [record] 动态注册广播

    /**
     * 动态注册广播
     */
    public void addAction(String action, BroadcastReceiver receiver) {
        Map<String, BroadcastReceiver> map;
        if (receiverMap.containsKey(action)) {
            map = this.receiverMap.get(action);
        } else {
            map = new LinkedHashMap<>();
        }
        String name = receiver.getClass().getName();
        // 获取当前广播所在类的全类名
        String qualifiedName = name.substring(0, name.indexOf("$"));
        if (map.containsKey(qualifiedName)) {
            // 一个类中有且只能注册同一Action一次
            return;
        }
        // 常规步奏动态注册广播
        IntentFilter filter = new IntentFilter();
        filter.addAction(action);
        mContext.registerReceiver(receiver, filter);
        // 动态注册广播后，将其加入广播集合
        map.put(qualifiedName, receiver);
        receiverMap.put(action, map);
    }

    // TODO: [record] 销毁动态注册的广播

    /**
     * 销毁动态注册的广播
     */
    public void destroy(Object cls, String action) {
        if (receiverMap.containsKey(action)) {
            Map<String, BroadcastReceiver> map = this.receiverMap.get(action);
            String qualifiedName = cls.getClass().getName();
            if (map.containsKey(qualifiedName)) {
                BroadcastReceiver receiver = map.remove(qualifiedName);
                if (null != receiver) {
                    mContext.unregisterReceiver(receiver);
                }
            }
        }
    }
}