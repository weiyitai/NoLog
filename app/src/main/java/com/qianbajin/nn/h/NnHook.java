package com.qianbajin.nn.h;

import com.qianbajin.nn.Util;

/**
 * @author qianbajin weiyitai
 * @date 2022/8/20  22:04
 */
public class NnHook implements IHook {

    @Override
    public void hook() {
        ClassLoader loader = Util.getClassLoader();
    }
}
