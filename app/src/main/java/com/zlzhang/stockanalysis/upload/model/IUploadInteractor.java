package com.zlzhang.stockanalysis.upload.model;


import com.zlzhang.stockmodel.StockModel;

import java.util.List;

/**
 * Created by zhangzhilai on 2018/7/29.
 */

public interface IUploadInteractor {

    void uploadStocks(List<StockModel> stockModelList, OnUploadListener onUploadListener);

    void uploadStock(StockModel stockModel);

    void uploadStockInfo(List<StockModel> stockModelList);

    interface OnUploadListener{
        void onUploadSucceed();
        void onUploadFailed();
    }
}
