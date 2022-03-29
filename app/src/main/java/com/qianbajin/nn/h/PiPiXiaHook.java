package com.qianbajin.nn.h;

import android.app.Activity;
import android.graphics.Color;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import com.qianbajin.nn.Util;

import java.lang.reflect.Method;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
/**
 * @author qianbajin
 * @date at 2021/8/28 0028  10:26
 */
public class PiPiXiaHook {

    private static final String TAG = "PiPiXiaHook";

    private int mColor = Color.parseColor("#BAB3C1");

    public void hook() {
        try {
            Class<?> StatusBarHelper = Util.getClassLoader().loadClass("com.sup.android.utils.StatusBarHelper");
            Method changeStatusBarTopMargin = XposedHelpers.findMethodExact(StatusBarHelper, "changeStatusBarTopMargin", boolean.class);
            Method modifySlideStatusBar = XposedHelpers.findMethodExact(StatusBarHelper, "modifySlideStatusBar", boolean.class);
            Log.d(TAG, "changeStatusBarTopMargin:" + changeStatusBarTopMargin);
            Method modifyStatusBar = XposedHelpers.findMethodExact(StatusBarHelper, "modifyStatusBar", boolean.class);
            XposedBridge.hookMethod(modifyStatusBar, new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    super.beforeHookedMethod(param);
                    Object arg0 = param.args[0];
//                    Log.d(TAG, "modifyStatusBar:" + param.thisObject + " " + arg0);
                    if (((boolean) arg0)) {
                        param.setResult(null);
                        changeStatusBarTopMargin.invoke(param.thisObject, false);
                        Activity activity = (Activity) XposedHelpers.getObjectField(param.thisObject, "activity");
                        setColor(activity.getWindow(), mColor);
                    }
                }
            });
            XposedBridge.hookMethod(modifySlideStatusBar, new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    super.beforeHookedMethod(param);
                    Log.d(TAG, "param.args[0]:" + param.args[0]);
                    Activity activity = (Activity) XposedHelpers.getObjectField(param.thisObject, "activity");
                    if (activity != null) {
                        setColor(activity.getWindow(), Color.TRANSPARENT);
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setColor(Window window, int color) {
        if (window != null) {
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(color);
        }
    }
}
