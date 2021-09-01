package com.qianbajin.nn.h;

import android.app.AndroidAppHelper;
import android.content.Context;
import android.content.SharedPreferences;
import android.text.format.DateUtils;
/**
 * @author qianbajin
 * @date at 2021/9/1 0001  22:46
 */
public class HookKeLe {

    public void hook() {
        SharedPreferences sp = AndroidAppHelper.currentApplication().getSharedPreferences("config", Context.MODE_PRIVATE);
        sp.edit().putLong("free_ad_time", System.currentTimeMillis() + DateUtils.DAY_IN_MILLIS).apply();
    }
}
