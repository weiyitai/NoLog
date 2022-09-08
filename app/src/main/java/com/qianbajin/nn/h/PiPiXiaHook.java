package com.qianbajin.nn.h;

import android.app.Activity;
import android.app.AndroidAppHelper;
import android.graphics.Color;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.qianbajin.nn.Util;

import java.lang.reflect.Method;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
/**
 * @author qianbajin
 * @date at 2021/8/28 0028  10:26
 */
public class PiPiXiaHook implements IHook {

    private static final String TAG = "PiPiXiaHook";

    private final int mColor = Color.parseColor("#BAB3C1");

    @Override
    public void hook() {
        try {
            Class<?> StatusBarHelper = Util.getClassLoader().loadClass("com.sup.android.utils.StatusBarHelper");
            Method changeStatusBarTopMargin = XposedHelpers.findMethodExact(StatusBarHelper, "changeStatusBarTopMargin", boolean.class);
            Method modifySlideStatusBar = XposedHelpers.findMethodExact(StatusBarHelper, "modifySlideStatusBar", boolean.class);
            Log.d(TAG, "changeStatusBarTopMargin:" + changeStatusBarTopMargin);
            Method modifyStatusBar = XposedHelpers.findMethodExact(StatusBarHelper, "modifyStatusBar", boolean.class);
            XposedBridge.hookMethod(modifyStatusBar, new XC_MethodHook() {
                private long lastHook;
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    super.beforeHookedMethod(param);
                    long now = System.currentTimeMillis();
                    if (now - lastHook < 300) {
                        Toast.makeText(AndroidAppHelper.currentApplication(), "间隔太短", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    lastHook = now;
                    Object arg0 = param.args[0];
//                    Log.d(TAG, "modifyStatusBar:" + param.thisObject + " " + arg0);
                    if (((boolean) arg0)) {
                        Log.d(TAG, "modifyStatusBar");
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
            Log.e(TAG, Log.getStackTraceString(e));
        }
    }

    private void setColor(Window window, int color) {
        if (window != null) {
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(color);
        }
    }
}
