package com.qianbajin.nn.h;

/**
 * ----------------------
 * 代码千万行
 * 注释第一行
 * 代码不注释
 * 改bug两行泪
 * -----------------------
 *
 * @author qianbajin
 * @date at 2020/1/12 0012  14:03
 */
public class QqHook extends BaseHook {

    @Override
    public void hook() {
        // H:.nixiang.qq.com.tencent.mobileqq-8.2.6-1320-3834.classes-dex2jar.jar!.com.tencent.qphone.base.util.QLog.class addLogItem()
        doNotThingAll(loadClass("com.tencent.qphone.base.util.QLog"), "addLogItem");
    }

}
