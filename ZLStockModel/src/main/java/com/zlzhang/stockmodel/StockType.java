package com.zlzhang.stockmodel;

/**
 * Created by zhangzhilai on 2018/3/6.
 */

public enum StockType {

    SH(1, "上证60"), SZ(2, "深圳"), SZ000(3, "深圳普通"), SZ002(4, "深圳中小"), SZ300(5, "深圳创业");

    int key;
    String value;

    StockType(int key, String value) {
        this.key = key;
        this.value = value;
    }
}
