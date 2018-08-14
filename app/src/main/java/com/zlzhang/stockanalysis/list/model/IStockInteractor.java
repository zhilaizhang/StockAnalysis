package com.zlzhang.stockanalysis.list.model;

import android.content.Context;


import com.zlzhang.stockmodel.StockModel;
import com.zlzhang.stockmodel.StockType;

import java.util.List;

/**
 * Created by zhangzhilai on 2018/3/6.
 */

public interface IStockInteractor {

    void getStockList(Context context, StockType type, final OnStockListener onStockListener);

    interface OnStockListener{
        void onStockGot(List<StockModel> stockModels);
    }
}
