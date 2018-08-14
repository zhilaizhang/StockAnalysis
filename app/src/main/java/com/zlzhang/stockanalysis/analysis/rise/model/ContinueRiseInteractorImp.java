package com.zlzhang.stockanalysis.analysis.rise.model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zlzhang.client.base.Action;
import com.zlzhang.client.handler.ActionHandler;
import com.zlzhang.stockanalysis.StockAnalysisUtil;
import com.zlzhang.stockanalysis.client.GetAllStocksAction;
import com.zlzhang.stockanalysis.modle.GlobalVariable;
import com.zlzhang.stockanalysis.modle.ResultData;
import com.zlzhang.stockanalysis.modle.StockModel;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by zhangzhilai on 2018/3/6.
 */

public class ContinueRiseInteractorImp implements IContinueRiseInteractor{

    private Gson mGson;

    public ContinueRiseInteractorImp(){
        mGson = new Gson();
    }

    @Override
    public void getAllStocksByTime(final String startTime, final String endTime, final OnStockListener onStockListener) {
//        Action.BASE_URL = "http://192.168.1.101:8080";
        String url = "http://192.168.1.101:8080/GetAllStocksAction";
        GetAllStocksAction getAllStocksAction = new GetAllStocksAction(url, startTime, endTime);
        getAllStocksAction.execute(true, new ActionHandler() {
            @Override
            public void doActionStart() {

            }

            @Override
            public void doActionEnd() {

            }

            @Override
            public void doActionResponse(int i, Serializable serializable) {
                if (serializable != null) {
                    String stocks = serializable.toString();
                    ResultData resultData = mGson.fromJson(stocks, ResultData.class);
                    if (resultData != null && resultData.getCode() == 0) {
                        onStockListener.onStocksGot(resultData.getResult());
                    }

                }

            }

            @Override
            public void doActionRawData(Serializable serializable) {

            }
        });
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                String url = "http://" + GlobalVariable.sServerIp + ":8080/GetAllStocksAction";
//                String params = "startTime=" + startTime + "&endTime=" + endTime;
//                String stocks = StockAnalysisUtil.getHttpURLConnection(url, params);
//                Map<String, List<StockModel>> stockMap = mGson.fromJson(stocks, new TypeToken<Map<String,List<StockModel>>>(){}.getType());
//                onStockListener.onStocksGot(stockMap);
//            }
//        }).start();

    }

    @Override
    public void getStocksByCodeAndTime(String code, String startTime, String endTime, OnStockListener onStockListener) {

    }
}
