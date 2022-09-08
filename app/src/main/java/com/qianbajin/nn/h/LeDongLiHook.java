package com.qianbajin.nn.h;

import android.util.Log;

import com.qianbajin.nn.Util;

import java.lang.reflect.Method;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.XposedHelpers;

/**
 * @author qianbajin weiyitai
 * @date 2022/9/8  22:49
 */
public class LeDongLiHook implements IHook {

    private static final String TAG = LeDongLiHook.class.getSimpleName();

    @Override
    public void hook() {
        ClassLoader loader = Util.getClassLoader();
        try {
            Class<?> SplashScreenActivity = loader.loadClass("cn.ledongli.ldl.activity.SplashScreenActivity");
            XposedHelpers.findAndHookMethod(SplashScreenActivity, "realInitActivity", new XC_MethodReplacement() {
                @Override
                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                    Method waitingForInit = XposedHelpers.findMethodExact(SplashScreenActivity, "waitingForInit");
                    waitingForInit.invoke(param.thisObject);
                    Log.d(TAG, "call waitingForInit");
                    return null;
                }
            });

            Class<?> AppCompatActivity = loader.loadClass("androidx.appcompat.app.AppCompatActivity");
            XposedHelpers.findAndHookMethod("cn.ledongli.ldl.utils.UserRestUsageManager", loader, "enterApp",
                AppCompatActivity, boolean.class, new XC_MethodHook() {
                    @Override
                    protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                        super.beforeHookedMethod(param);
                        param.args[1] = false;
                        Log.d(TAG, "enterApp beforeHookedMethod");
                    }
                });
        } catch (Exception e) {
            Log.e(TAG, Log.getStackTraceString(e));
        }
    }
}
