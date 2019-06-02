package com.qianbajin.nn;

import android.util.Log;

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

    void hook(ClassLoader loader) throws Exception {

        Class<?> aClass = loader.loadClass("com.tencent.base.debug.Tracer");
        Method trace = XposedHelpers.findMethodExact(aClass, "trace", int.class,
                Thread.class, long.class, String.class, String.class, Throwable.class);
        XposedBridge.hookMethod(trace, new XC_MethodReplacement() {
            @Override
            protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                return null;
            }
        });

        Class<?> logOutputManager = loader.loadClass("com.tencent.qqmusic.log.LogOutputManager");
        Method output = XposedHelpers.findMethodExact(logOutputManager, "output",
                String.class, String.class, String.class, String.class);
        Log.d(TAG, "output:" + output);
        XposedBridge.hookMethod(output, new XC_MethodReplacement() {
            @Override
            protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                return null;
            }
        });
    }

}
