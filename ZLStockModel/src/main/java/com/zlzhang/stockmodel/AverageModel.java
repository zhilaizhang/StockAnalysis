package com.zlzhang.stockmodel;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zhangzhilai on 2018/3/4.
 * 均价数据结构
 */

public class AverageModel implements Serializable {

    private int id;                 //股票代码对应的唯一id
    private String code;           //股票代码
    private String name;           //股票名称
    private List<Float> prices;    //每天的收盘价
    private float averagePrice;        //均价
    private int days;

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

    public float getAveragePrice() {
        return averagePrice;
    }

    public void setAveragePrice(float averagePrice) {
        this.averagePrice = averagePrice;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }
}
