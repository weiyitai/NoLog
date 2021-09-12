package com.qianbajin.nn.h;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.text.format.DateUtils;
import android.util.Log;

import java.lang.reflect.Method;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
/**
 * @author qianbajin
 * @date at 2021/9/1 0001  22:46
 */
public class HookKeLe {

    public void hook() {
        Method onCreate = XposedHelpers.findMethodExact(Application.class, "onCreate");
        XposedBridge.hookMethod(onCreate, new XC_MethodHook() {
            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                super.afterHookedMethod(param);
                SharedPreferences sp = ((Application) param.thisObject).getSharedPreferences("config", Context.MODE_PRIVATE);
                sp.edit().putLong("free_ad_time", System.currentTimeMillis() + DateUtils.DAY_IN_MILLIS).apply();
                Log.d("HookKeLe", "HookKeLe success");
            }
        });
    }
}
