package com.zlzhang.stockmodel;

import java.io.Serializable;

/**
 * Created by zhangzhilai on 2018/3/4.
 * 股票基本信息表，id对应股票代码
 * 1<---->1
 */

public class StockInfo implements Serializable{

    private int id;                 //股票代码对应的唯一id
    private String code;           //股票代码
    private String name;           //股票名称

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
