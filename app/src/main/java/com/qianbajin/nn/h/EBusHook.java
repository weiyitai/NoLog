package com.qianbajin.nn.h;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
/**
 * @author Administrator
 * @date at 2019/6/15 0015  10:24
 */
public class EBusHook {

    private static final String TAG = "EBusHook";

    public void hook(ClassLoader loader) {
        try {
            Class<?> WelComeActivity = loader.loadClass("zxzs.ppgj.ui.activity.WelComeActivity");
            Method onCreate = XposedHelpers.findMethodExact(WelComeActivity, "onCreate", Bundle.class);
            XposedBridge.hookMethod(onCreate, new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    super.beforeHookedMethod(param);
                }

                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    super.afterHookedMethod(param);
                    Activity activity = (Activity) param.thisObject;
                    Class<?> wallnewactivity = loader.loadClass("zxzs.ppgj.ui.activity.WallNewActivity");
                    Intent intent = new Intent(activity, wallnewactivity);
                    intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    activity.startActivity(intent);
                    activity.finish();
                }
            });
            Class<?> b = loader.loadClass("zxw.ebus.data.e.b");
            Method a = b.getDeclaredMethod("a", String.class);
            XposedBridge.hookMethod(a, new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    super.beforeHookedMethod(param);
                    Log.d(TAG, "param.args:" + Arrays.toString(param.args));
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void setComponentClass(ComponentName componentName, String className) throws NoSuchFieldException, IllegalAccessException {
        Class<? extends ComponentName> componentNameClass = componentName.getClass();
        Field mClass = componentNameClass.getDeclaredField("mClass");
        mClass.setAccessible(true);
        mClass.set(componentName, className);
    }

}
