package com.zlzhang.stockanalysis.upload.presenter;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

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

    private static final int UPLOAD_SUCCEED = 0x01;
    private static final int UPLOAD_FAILED = 0x02;

    private IUploadView mUploadView;
    private Context mContext;
    private StockDataCache mStockDataCache;
    private IUploadInteractor mIUploadInteractor;
    private Handler mHandler;


    public UploadPresenterImp(IUploadView uploadView, Context context){
        mUploadView = uploadView;
        mContext = context;
        mStockDataCache = StockDataCache.getInstance();
        mIUploadInteractor = new UploadInteractorImp();
        initHandler();
    }

    private void initHandler() {
        mHandler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what){
                    case UPLOAD_SUCCEED:
                        mUploadView.uploadHint("上传成功");
                        break;
                    case UPLOAD_FAILED:
                        mUploadView.uploadHint("上传失败");
                        break;
                }
            }
        };
    }


    @Override
    public void uploadStocks() {
        List<StockModel> stockModels = mStockDataCache.getAllStockModelList();
        mIUploadInteractor.uploadStocks(stockModels, new IUploadInteractor.OnUploadListener() {
            @Override
            public void onUploadSucceed() {
                Message message = new Message();
                message.what = UPLOAD_SUCCEED;
                mHandler.sendMessage(message);
            }

            @Override
            public void onUploadFailed() {
                Message message = new Message();
                message.what = UPLOAD_FAILED;
                mHandler.sendMessage(message);
            }
        });
    }

    @Override
    public void uploadStock() {

    }

    @Override
    public void uploadStockInfo() {
        List<StockModel> stockModels = mStockDataCache.getAllStockModelList();
        mIUploadInteractor.uploadStockInfo(stockModels);
    }
}
