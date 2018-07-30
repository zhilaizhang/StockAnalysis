package com.zlzhang.stockanalysis.upload.model;

import com.google.gson.Gson;
import com.zlzhang.stockanalysis.StockAnalysisUtil;
import com.zlzhang.stockanalysis.modle.StockModel;

import java.util.List;

/**
 * Created by zhangzhilai on 2018/7/29.
 */

public class UploadInteractorImp implements IUploadInteractor{


    @Override
    public void uploadStocks(List<StockModel> stockModelList) {
        Gson gson = new Gson();
        final String stocks = gson.toJson(stockModelList);
        final String url = "http://192.168.1.101:8080/StockServer";
        new Thread(new Runnable() {
            @Override
            public void run() {
                StockAnalysisUtil.postQuery(url, stocks);
            }
        }).start();

    }

    @Override
    public void uploadStock(StockModel stockModel) {

    }
}
