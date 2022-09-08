package com.qianbajin.nn.h;

import android.util.Log;

import com.qianbajin.nn.Util;

import java.lang.reflect.Method;

import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
/**
 * @author Administrator
 * @date at 2019/6/2 0002  21:33
 */
public class QqMusicHook {

    private static final String TAG = "QqMusicHook";

    void hook() {
        try {
            ClassLoader loader = Util.getClassLoader();
            Class<?> tracer = loader.loadClass("com.tencent.base.debug.Tracer");
            Method trace = XposedHelpers.findMethodExact(tracer, "trace", int.class,
                    Thread.class, long.class, String.class, String.class, Throwable.class);
            XposedBridge.hookMethod(trace, XC_MethodReplacement.DO_NOTHING);

            Class<?> logOutputManager = loader.loadClass("com.tencent.qqmusic.log.LogOutputManager");
            Method output = XposedHelpers.findMethodExact(logOutputManager, "output",
                    String.class, String.class, String.class, String.class);
            Log.d(TAG, "output:" + output);
            XposedBridge.hookMethod(output, XC_MethodReplacement.DO_NOTHING);
            // 9.10.0.7  64位 Google 版本 禁用开屏广告方法
            // 修改 /data/data/com.tencent.qqmusic/app_adnet 权限为 000
        } catch (Exception e) {
            e.printStackTrace();
            Log.d(TAG, Log.getStackTraceString(e));
        }
    }

}
