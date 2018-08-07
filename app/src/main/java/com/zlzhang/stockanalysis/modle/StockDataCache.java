package com.zlzhang.stockanalysis.modle;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangzhilai on 2018/3/13.
 */

public class StockDataCache {

    private static StockDataCache mStockDataCache;

    public  List<StockModel>  mSHStockModelList;

    public  List<StockModel>  mSZStockModelList;

    public static StockDataCache getInstance(){
        if (mStockDataCache == null) {
            mStockDataCache = new StockDataCache();
        }
        return mStockDataCache;
    }


    public List<StockModel> getmSHStockModelList() {
        return mSHStockModelList;
    }

    public void setmSHStockModelList(List<StockModel> mSHStockModelList) {
        this.mSHStockModelList = mSHStockModelList;
    }

    public List<StockModel> getmSZStockModelList() {
        return mSZStockModelList;
    }

    public void setmSZStockModelList(List<StockModel> mSZStockModelList) {
        this.mSZStockModelList = mSZStockModelList;
    }

    public List<StockModel> getAllStockModelList(){
        List<StockModel> stockModels = new ArrayList<>();
        stockModels.addAll(mSHStockModelList);
        stockModels.addAll(mSZStockModelList);
        return stockModels;
    }

}
