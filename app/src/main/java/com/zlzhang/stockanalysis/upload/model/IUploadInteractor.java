package com.zlzhang.stockanalysis.upload.model;

import com.zlzhang.stockanalysis.modle.StockModel;

import java.util.List;

/**
 * Created by zhangzhilai on 2018/7/29.
 */

public interface IUploadInteractor {

    void uploadStocks(List<StockModel> stockModelList);

    void uploadStock(StockModel stockModel);
}
