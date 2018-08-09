package com.zlzhang.stockanalysis.list.model;

import android.content.Context;

import com.zlzhang.stockanalysis.modle.StockModel;
import com.zlzhang.stockanalysis.modle.StockType;

import java.util.List;

/**
 * Created by zhangzhilai on 2018/3/6.
 */

public interface ContinueRiseInteractorImp {

    void getStockList(Context context, StockType type, final OnStockListener onStockListener);

    interface OnStockListener{
        void onStockGot(List<StockModel> stockModels);
    }
}
