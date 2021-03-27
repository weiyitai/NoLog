package com.qianbajin.nn.h;

import android.util.Log;

import com.qianbajin.nn.Util;

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
 * @date at 2020/1/12 0012  14:13
 */
abstract class BaseHook {

    private final String TAG = getClass().getSimpleName();
    private ClassLoader loader = Util.getClassLoader();

    /**
     * hook 方法
     */
    abstract void hook();

    Class<?> loadClass(String className) {
        try {
            return loader.loadClass(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            log(e);
        }
        return null;
    }

    /**
     * 使方法不执行
     *
     * @param aClass 类名
     * @param method 方法
     */
    void doNotThing(Class<?> aClass, Method method) {
        try {
            XposedBridge.hookMethod(method, XC_MethodReplacement.DO_NOTHING);
        } catch (Exception e) {
            e.printStackTrace();
            log(e);
        }
    }

    /**
     * 使方法不执行
     *
     * @param aClass     类名
     * @param methodName 方法名
     */
    void doNotThingAll(Class<?> aClass, String methodName) {
        try {
            XposedBridge.hookAllMethods(aClass, methodName, XC_MethodReplacement.DO_NOTHING);
        } catch (Exception e) {
            e.printStackTrace();
            log(e);
        }
    }

    /**
     * 错误日志
     *
     * @param throwable 异常
     */
    protected void log(Throwable throwable) {
        Log.e(TAG, Log.getStackTraceString(throwable));
    }
}
