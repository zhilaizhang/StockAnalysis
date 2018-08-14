package com.zlzhang.stockmodel;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zhangzhilai on 2018/3/4.
 * 连涨数据结构
 */

public class ContinueRiseModel implements Serializable {

    private int id;                 //股票代码对应的唯一id
    private String code;           //股票代码
    private String name;           //股票名称
    private List<Float> prices;    //每天的收盘价
    private int continueDays;      //连涨天数
    private float riseRate;        //涨幅

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Float> getPrices() {
        return prices;
    }

    public void setPrices(List<Float> prices) {
        this.prices = prices;
    }

    public int getContinueDays() {
        return continueDays;
    }

    public void setContinueDays(int continueDays) {
        this.continueDays = continueDays;
    }

    public float getRiseRate() {
        return riseRate;
    }

    public void setRiseRate(float riseRate) {
        this.riseRate = riseRate;
    }

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
