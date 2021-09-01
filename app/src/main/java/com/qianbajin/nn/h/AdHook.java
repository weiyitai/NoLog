package com.qianbajin.nn.h;

import android.util.Log;

import com.qianbajin.nn.Constant;
import com.qianbajin.nn.Util;

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
            switch (packageName) {
                case Constant.PKG_QQ_MUSIC:
                    new QqMusicHook().hook(loader);
                    break;
                case Constant.PKG_WECHAT:
                    new XLogHook().hook();
                    break;
                case Constant.PKG_MOBILEQQ:
                    new QqHook().hook();
                    break;
                case Constant.PKG_WEISHI:
                    new TencentTracerHook().hook(loader);
                    break;
                case Constant.PKG_NOW:
                    new TencentTracerHook().hook(loader);
                    break;
                case Constant.PKG_FIND_DEVICE:
                    new FindDeviceHook().hook();
                    break;
                case Constant.PKG_MIPAY_WALLET:
                    new MiPayHook().hook();
                    break;
                case Constant.PKG_PIPIXIX:
                    new HookPiPiXia().hook();
                    break;
                case Constant.PKG_KELE:
                    new HookKeLe().hook();
                    break;
                default:break;
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, packageName);
        }
    }
}
