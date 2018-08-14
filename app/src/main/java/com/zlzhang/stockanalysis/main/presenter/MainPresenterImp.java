package com.zlzhang.stockanalysis.main.presenter;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

import com.zlzhang.stockanalysis.main.model.IMainInteractor;
import com.zlzhang.stockanalysis.main.model.MainInteractorImp;
import com.zlzhang.stockanalysis.main.view.IMainView;
import com.zlzhang.stockmodel.StockModel;

import java.util.List;

/**
 * Created by zhangzhilai on 2018/3/13.
 */

public class MainPresenterImp implements IMainPresenter {

    private static final int STOCK_SUCCEED = 0x01;
    private static final int STOCK_FAILED = 0x02;
    private static final int STOCK_COUNT = 0x03;

    private IMainInteractor mMainInteractor;
    private Handler mHandler;
    private IMainView mMainView;

    public MainPresenterImp(Context context, IMainView mainView){
        mMainInteractor = new MainInteractorImp(context);
        mMainView = mainView;
        initHandler();

    }

    private void initHandler() {
        mHandler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what){
                    case STOCK_SUCCEED:
                        mMainView.showLoadingView(false);
                        mMainView.dataGot(true);
                        break;
                    case STOCK_FAILED:
                        mMainView.showLoadingView(false);
                        mMainView.dataGot(false);
                        break;
                    case STOCK_COUNT:
                        mMainView.showCount(msg.arg1);
                        break;
                }
            }
        };
    }

    @Override
    public void getAllData() {
        mMainView.showLoadingView(true);
        mMainInteractor.getAllStocks(new IMainInteractor.OnGetDataListener() {
            @Override
            public void onGetSucceed(List<StockModel> stockModelList) {
                Message message = new Message();
                message.what = STOCK_SUCCEED;
                mHandler.sendMessage(message);
            }

            @Override
            public void onGetFailed() {
                Message message = new Message();
                message.what = STOCK_FAILED;
                mHandler.sendMessage(message);
            }

            @Override
            public void onProgress(int num) {
                Message message = new Message();
                message.what = STOCK_COUNT;
                message.arg1 = num;
                mHandler.sendMessage(message);
            }
        });
    }

    @Override
    public void uploadAllData() {
        mMainInteractor.uploadStocks(null, new IMainInteractor.OnUploadListener() {
            @Override
            public void onUploadSucceed() {

            }

            @Override
            public void onUploadFailed() {

            }
        });
    }
}
