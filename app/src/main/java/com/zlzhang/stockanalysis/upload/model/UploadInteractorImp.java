package com.zlzhang.stockanalysis.upload.model;

import com.google.gson.Gson;
import com.zlzhang.stockanalysis.StockAnalysisUtil;
import com.zlzhang.stockanalysis.modle.StockInfo;
import com.zlzhang.stockanalysis.modle.StockModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangzhilai on 2018/7/29.
 */

public class UploadInteractorImp implements IUploadInteractor{


    @Override
    public void uploadStocks(List<StockModel> stockModelList) {
        Gson gson = new Gson();
        final String stocks = gson.toJson(stockModelList);
        final String url = "http://192.168.1.100:8080/AddStockListAction";
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

    @Override
    public void uploadStockInfo(List<StockModel> stockModelList) {
        List<StockInfo>  stockInfos = new ArrayList<>();
        if (stockModelList != null) {
            for (StockModel stockModel : stockModelList) {
                StockInfo stockInfo = new StockInfo();
                stockInfo.setCode(stockModel.getCode());
                stockInfo.setName(stockModel.getName());
                stockInfos.add(stockInfo);
            }
            Gson gson = new Gson();
            final String infors = gson.toJson(stockInfos);
            final String url = "http://192.168.1.100:8080/AddStockInfoAction";
            new Thread(new Runnable() {
                @Override
                public void run() {
                    StockAnalysisUtil.postQuery(url, infors);
                }
            }).start();
        }
    }
}
