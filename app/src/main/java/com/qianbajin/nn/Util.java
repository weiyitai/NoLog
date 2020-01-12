package com.qianbajin.nn;

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
 * @date at 2020/1/12 0012  14:09
 */
public class Util {

    private static ClassLoader sLoader;

    public static ClassLoader getClassLoader() {
        if (sLoader == null) {
            sLoader = XposedBridge.BOOTCLASSLOADER;
        }
        return sLoader;
    }

    public static void setLoader(ClassLoader loader) {
        sLoader = loader;
    }
}
