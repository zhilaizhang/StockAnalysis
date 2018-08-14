package com.zlzhang.stockanalysis.upload.model;

import com.google.gson.Gson;
import com.zlzhang.stockanalysis.StockAnalysisUtil;
import com.zlzhang.stockmodel.GlobalVariable;
import com.zlzhang.stockmodel.ResultData;
import com.zlzhang.stockmodel.StockInfo;
import com.zlzhang.stockmodel.StockModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangzhilai on 2018/7/29.
 */

public class UploadInteractorImp implements IUploadInteractor{


    @Override
    public void uploadStocks(List<StockModel> stockModelList, final OnUploadListener onUploadListener) {
        final Gson gson = new Gson();
        final String stocks = gson.toJson(stockModelList);
        final String url = "http://" + GlobalVariable.sServerIp + ":8080/AddStockListAction";
        new Thread(new Runnable() {
            @Override
            public void run() {
                String result = StockAnalysisUtil.postQuery(url, stocks);
                ResultData resultData = gson.fromJson(result, ResultData.class);
                if (resultData.getCode() == 0) {
                    onUploadListener.onUploadSucceed();
                } else {
                    onUploadListener.onUploadFailed();
                }
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
