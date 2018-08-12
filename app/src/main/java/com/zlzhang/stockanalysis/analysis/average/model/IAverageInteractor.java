package com.zlzhang.stockanalysis.analysis.average.model;

import com.zlzhang.stockanalysis.modle.StockModel;

import java.util.List;
import java.util.Map;

/**
 * Created by zhangzhilai on 2018/3/6.
 */

public interface IAverageInteractor {

    void getAllStocksByTime(String startTime, String endTime, OnStockListener onStockListener);

    void getStocksByCodeAndTime(String code, String startTime, String endTime, OnStockListener onStockListener);

    interface OnStockListener{
        void onStocksGot(Map<String, List<StockModel>> stockModels);
    }
}
