package com.qianbajin.nn;

/**
 * @author qianbajin weiyitai
 * @date 2022/8/20  22:14
 */
public class ItemBean {

    private String pkgName;
    private boolean check;

    public ItemBean(String pkgName, boolean check) {
        this.pkgName = pkgName;
        this.check = check;
    }

    public String getPkgName() {
        return pkgName;
    }

    public void setPkgName(String pkgName) {
        this.pkgName = pkgName;
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }
}
