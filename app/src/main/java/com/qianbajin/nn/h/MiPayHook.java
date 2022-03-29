package com.qianbajin.nn.h;

import android.util.Log;

import com.qianbajin.nn.Util;

import java.lang.reflect.Method;

import de.robv.android.xposed.XposedHelpers;
/**
 * ----------------------
 * 代码千万行
 * 注释第一行
 * 代码不注释
 * 改bug两行泪
 * -----------------------
 * @author qianbajin
 * @date at 2021/3/27 0027  22:47
 */
public class MiPayHook extends BaseHook {

    private static final String TAG = "MiPayHook";

    @Override
    public void hook() {
        try {
            Class<?> HomePageFragment = Util.getClassLoader().loadClass("com.mipay.home.ui.HomePageFragment");
            Method a = XposedHelpers.findMethodExact(HomePageFragment, "a");
            Log.d(TAG, "a:" + a);
            doNotThing(HomePageFragment, a);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
