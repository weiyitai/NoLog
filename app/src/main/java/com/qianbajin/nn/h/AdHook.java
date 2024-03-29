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
                    new QqMusicHook().hook();
                    break;
                case Constant.PKG_WECHAT:
                    new XLogHook().hook();
                    break;
                case Constant.PKG_MOBILEQQ:
                    new QqHook().hook();
                    break;
                // case Constant.PKG_WEISHI:
                // case Constant.PKG_NOW:
                //     new TencentTracerHook().hook();
                //     break;
                case Constant.PKG_FIND_DEVICE:
                    new FindDeviceHook().hook();
                    break;
                case Constant.PKG_MIPAY_WALLET:
                    new MiPayHook().hook();
                    break;
                case Constant.PKG_PIPIXIX:
                    new PiPiXiaHook().hook();
                    break;
                case Constant.PKG_LEDONGLI:
                    new LeDongLiHook().hook();
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            Log.e(TAG, Log.getStackTraceString(e));
            throw e;
        }
    }
}
