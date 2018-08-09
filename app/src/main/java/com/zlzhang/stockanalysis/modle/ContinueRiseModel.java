package com.zlzhang.stockanalysis.modle;

import java.io.Serializable;

/**
 * Created by zhangzhilai on 2018/3/4.
 *
 *
 */

public class ContinueRiseModel implements Serializable{

    private int id;                 //股票代码对应的唯一id
    private String code;           //股票代码
    private String name;           //股票名称
    private List<Float> prices;    //每天的收盘价
    private int continueDays;      //连涨天数
    private float

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
