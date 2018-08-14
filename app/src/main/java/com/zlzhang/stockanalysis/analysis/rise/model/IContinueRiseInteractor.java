package com.zlzhang.stockanalysis.analysis.rise.model;


import com.zlzhang.stockmodel.ContinueRiseModel;
import com.zlzhang.stockmodel.StockModel;

import java.util.List;
import java.util.Map;

/**
 * Created by zhangzhilai on 2018/3/6.
 */

public interface IContinueRiseInteractor {

    void getAllStocksByTime(int days, OnStockListener onStockListener);

    void getStocksByCodeAndTime(String code, String startTime, String endTime, OnStockListener onStockListener);

    interface OnStockListener{
        void onStocksGot(List<ContinueRiseModel> stockModels);
    }
}
