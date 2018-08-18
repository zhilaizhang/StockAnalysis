package com.zlzhang.stockanalysis.analysis.average.model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zlzhang.client.handler.ActionHandler;
import com.zlzhang.stockanalysis.client.GetContinueRiseStocksAction;
import com.zlzhang.stockmodel.AverageModel;
import com.zlzhang.stockmodel.ContinueRiseModel;
import com.zlzhang.stockmodel.GlobalVariable;
import com.zlzhang.stockmodel.ResultData;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zhangzhilai on 2018/3/6.
 */

public class AverageInteractorImp implements IAverageInteractor {

    private Gson mGson;

    public AverageInteractorImp(){
        mGson = new Gson();
    }

    @Override
    public void getAllStocksByTime(int days, final OnStockListener onStockListener) {
        String url = "http://" + GlobalVariable.sServerIp + ":8080/GetStocksAverageByDaysAction";
        GetContinueRiseStocksAction getAllStocksAction = new GetContinueRiseStocksAction(url, days);
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
                        String result = resultData.getResult();
                        List<AverageModel> continueRiseModelList = mGson.fromJson(result, new TypeToken<List<AverageModel>>(){}.getType());
                        onStockListener.onStocksGot(continueRiseModelList);
                    }

                }

            }

            @Override
            public void doActionRawData(Serializable serializable) {

            }
        });
    }

    @Override
    public void getStocksByCodeAndTime(String code, String startTime, String endTime, OnStockListener onStockListener) {

    }
}
