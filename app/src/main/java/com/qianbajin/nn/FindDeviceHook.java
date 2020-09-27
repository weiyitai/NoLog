package com.qianbajin.nn;

import android.util.Log;

import java.lang.reflect.Method;

import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
/**
 * ----------------------
 * 代码千万行
 * 注释第一行
 * 代码不注释
 * 改bug两行泪
 * -----------------------
 *
 * @author qianbajin
 * @date at 2020/9/27 0027  21:12
 */
public class FindDeviceHook {

    private static final String TAG = FindDeviceHook.class.getSimpleName();

    void hook() {
        try {
            Class<?> XLogger = Util.getClassLoader().loadClass("miui.cloud.common.XLogger");
            Class<?> SwitchFileLogSender = Util.getClassLoader().loadClass("miui.cloud.common.SwitchFileLogSender");
            Class<?> EncryptLogSender = Util.getClassLoader().loadClass("com.xiaomi.finddevice.common.log.EncryptLogSender");
            Method logAtLevelImp = XposedHelpers.findMethodExact(XLogger, "logAtLevelImp", int.class, String.class, Object[].class);
            XposedBridge.hookMethod(logAtLevelImp, XC_MethodReplacement.DO_NOTHING);
            Method sendLogLocked = XposedHelpers.findMethodExact(SwitchFileLogSender, "sendLogLocked", int.class, String.class, String.class);
            Method sendLog = XposedHelpers.findMethodExact(EncryptLogSender, "sendLog", int.class, String.class, String.class);
            XposedBridge.hookMethod(sendLogLocked, XC_MethodReplacement.DO_NOTHING);
            XposedBridge.hookMethod(sendLog, XC_MethodReplacement.DO_NOTHING);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            Log.d(TAG, Log.getStackTraceString(e));
        }
    }

}
