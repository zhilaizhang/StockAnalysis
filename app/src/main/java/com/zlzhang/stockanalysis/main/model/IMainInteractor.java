package com.zlzhang.stockanalysis.main.model;

import com.zlzhang.stockanalysis.modle.StockModel;

import java.util.List;

/**
 * Created by zhangzhilai on 2018/3/13.
 */

public interface IMainInteractor {


    void getAllStocks(OnGetDataListener onGetDataListener);

    void uploadStocks(List<StockModel> stockModels, OnUploadListener onUploadListener);

    interface OnGetDataListener{
        void onGetSucceed(List<StockModel> stockModelList);
        void onGetFailed();
        void onProgress(int num);
    }

    interface OnUploadListener{
        void onUploadSucceed();
        void onUploadFailed();
    }
}
