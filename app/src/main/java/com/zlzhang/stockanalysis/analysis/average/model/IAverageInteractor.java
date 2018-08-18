package com.zlzhang.stockanalysis.analysis.average.model;


import com.zlzhang.stockmodel.AverageModel;
import com.zlzhang.stockmodel.StockModel;

import java.util.List;
import java.util.Map;

/**
 * Created by zhangzhilai on 2018/3/6.
 */

public interface IAverageInteractor {

    void getAllStocksByTime(int days, OnStockListener onStockListener);

    void getStocksByCodeAndTime(String code, String startTime, String endTime, OnStockListener onStockListener);

    interface OnStockListener{
        void onStocksGot(List<AverageModel> stockModels);
    }
}
