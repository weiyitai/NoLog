package com.qianbajin.nn;

import java.lang.reflect.Method;

import de.robv.android.xposed.XC_MethodReplacement;
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
            Class<?> xLog = loader.loadClass("com.tencent.mars.xlog.Xlog");
            Method[] declaredMethods = xLog.getDeclaredMethods();
            for (Method method : declaredMethods) {
                String name= method.getName();
                if (name.startsWith("log")) {
                    XposedBridge.hookMethod(method, XC_MethodReplacement.DO_NOTHING);
                }
            }
//            XposedBridge.hookAllMethods(xLog, "logWrite2", XC_MethodReplacement.DO_NOTHING);
//            XposedBridge.hookAllMethods(xLog, "logWrite", XC_MethodReplacement.DO_NOTHING);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

}
