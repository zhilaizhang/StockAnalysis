package com.zlzhang.stockanalysis.upload.presenter;

import android.content.Context;

import com.zlzhang.stockanalysis.StockAnalysisUtil;
import com.zlzhang.stockanalysis.modle.StockDataCache;
import com.zlzhang.stockanalysis.modle.StockModel;
import com.zlzhang.stockanalysis.upload.model.IUploadInteractor;
import com.zlzhang.stockanalysis.upload.model.UploadInteractorImp;
import com.zlzhang.stockanalysis.upload.view.IUploadView;

import java.util.List;

/**
 * Created by zhangzhilai on 2018/7/29.
 */

public class UploadPresenterImp implements IUploadPresenter {

    private IUploadView mUploadView;
    private Context mContext;
    private StockDataCache mStockDataCache;
    private IUploadInteractor mIUploadInteractor;


    public UploadPresenterImp(IUploadView uploadView, Context context){
        mUploadView = uploadView;
        mContext = context;
        mStockDataCache = StockDataCache.getInstance();
        mIUploadInteractor = new UploadInteractorImp();
    }


    @Override
    public void uploadStocks() {
        List<StockModel> stockModels = mStockDataCache.getAllStockModelList();
        mIUploadInteractor.uploadStocks(stockModels);
    }

    @Override
    public void uploadStock() {

    }
}
