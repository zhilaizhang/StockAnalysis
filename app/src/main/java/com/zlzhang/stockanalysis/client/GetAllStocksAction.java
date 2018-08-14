package com.zlzhang.stockanalysis.client;

import android.util.Pair;

import com.zlzhang.client.base.BaseGetAction;

import java.util.List;

/**
 * Created by zhangzhilai on 2018/8/13.
 */

public class GetAllStocksAction extends BaseGetAction {

    private String startTime;
    private String endTime;


    public GetAllStocksAction(String url, String startTime, String endTime){
        super(url);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    protected void doResponse(String s) throws Exception {
        setData(s);
    }

    @Override
    public void setParam(List<Pair> list) {
        mParams.add(new Pair("startTime", startTime));
        mParams.add(new Pair("endTime", endTime));
    }
}
