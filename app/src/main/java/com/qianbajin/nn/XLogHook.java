package com.qianbajin.nn;

import android.util.Log;

import java.lang.reflect.Field;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
/**
 * ----------------------
 * 代码千万行
 * 注释第一行
 * 代码不注释
 * 改bug两行泪
 * -----------------------
 *
 * @author qianbajin
 * @date at 2020/1/11 0011  19:41
 */
public class XLogHook {

    void hook(ClassLoader loader) {
        try {
            Class<?> Xlog = Util.getClassLoader().loadClass("com.tencent.mars.xlog.Xlog");
            XposedBridge.hookAllMethods(Xlog, "appenderOpen", new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    super.beforeHookedMethod(param);
                    Object arg = param.args[0];
                    printObj(arg);
                    param.setResult(null);
                }
            });

            // public static native void appenderOpen(int var0, int var1, String var2, String var3, String var4, int var5, String var6);
//            Class<?> xLog = loader.loadClass("com.tencent.mars.xlog.Xlog");
//            Method appenderOpen = XposedHelpers.findMethodExact(xLog, "appenderOpen", int.class, int.class, String.class, String.class,
//                    String.class, int.class, String.class);
//            XposedBridge.hookMethod(appenderOpen, XC_MethodReplacement.DO_NOTHING);
//            Method[] declaredMethods = xLog.getDeclaredMethods();
//            for (Method method : declaredMethods) {
//                String name= method.getName();
//                if (name.startsWith("log")) {
//                    XposedBridge.hookMethod(method, XC_MethodReplacement.DO_NOTHING);
//                }
//            }
//            XposedBridge.hookAllMethods(xLog, "logWrite2", XC_MethodReplacement.DO_NOTHING);
//            XposedBridge.hookAllMethods(xLog, "logWrite", XC_MethodReplacement.DO_NOTHING);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    private void printObj(Object arg) {
        try {
            Field[] declaredFields = arg.getClass().getDeclaredFields();
            StringBuilder builder = new StringBuilder(declaredFields.length * 8);
            for (Field field : declaredFields) {
                builder.append(field.getName()).append(':').append(field.get(arg)).append(',');
            }
            Log.d("XLogHook", "builder:" + builder);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

}
