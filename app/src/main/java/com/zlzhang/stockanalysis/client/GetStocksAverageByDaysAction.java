package com.zlzhang.stockanalysis.client;

import android.util.Pair;

import com.zlzhang.client.base.BaseGetAction;

import java.util.List;

/**
 * Created by zhangzhilai on 2018/8/18.
 * 获取站上均线的股票
 */

public class GetStocksAverageByDaysAction extends BaseGetAction {

    private int days;

    public GetStocksAverageByDaysAction(String url, int days){
        super(url);
        this.days = days;
    }

    @Override
    protected void doResponse(String s) throws Exception {
        setData(s);
    }

    @Override
    public void setParam(List<Pair> list) {
        mParams.add(new Pair("days", days));
    }
}
