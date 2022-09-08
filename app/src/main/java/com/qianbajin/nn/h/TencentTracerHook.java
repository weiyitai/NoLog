package com.qianbajin.nn.h;

import com.qianbajin.nn.Util;

import java.lang.reflect.Method;

import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
/**
 * ----------------------
 * 代码千万行
 * 注释第一行
 * 代码不注释
 * 改gub两行泪
 * -----------------------
 *
 * @author weiyitai
 * @date 2019/6/24 0024  9:03
 */
public class TencentTracerHook {

    void hook() throws Exception {
        ClassLoader loader = Util.getClassLoader();
        Class<?> tracer = loader.loadClass("com.tencent.base.debug.Tracer");
        Method trace = XposedHelpers.findMethodExact(tracer, "trace", int.class,
            Thread.class, long.class, String.class, String.class, Throwable.class);
        XposedBridge.hookMethod(trace, XC_MethodReplacement.DO_NOTHING);
    }

}
