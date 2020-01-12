package com.qianbajin.nn;

import android.util.Log;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.callbacks.XC_LoadPackage;
/**
 * @author Administrator
 * @date at 2019/6/2 0002  18:17
 */
public class AdHook implements IXposedHookLoadPackage {

    private static final String TAG = "AdHook";

    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {
        String packageName = lpparam.packageName;
        ClassLoader loader = lpparam.classLoader;
        Util.setLoader(loader);
        try {
            if (Constant.PKG_QQ_MUSIC.equals(packageName)) {
                new QqMusicHook().hook(loader);
            } else if (Constant.PKG_WECHAT.equals(packageName)) {
                new XLogHook().hook(loader);
            } else if (Constant.PKG_MOBILEQQ.equals(packageName)) {
                new QqHook().hook();
            } else if (Constant.PKG_WEISHI.equals(packageName)) {
                new TencentTracerHook().hook(loader);
            } else if (Constant.PKG_NOW.equals(packageName)) {
                new TencentTracerHook().hook(loader);
            } else if (Constant.PKG_EBUS.equals(packageName)) {
                new EBusHook().hook(loader);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, packageName);
        }
    }
}
