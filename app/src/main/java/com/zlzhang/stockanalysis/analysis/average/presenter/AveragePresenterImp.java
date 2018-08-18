package com.zlzhang.stockanalysis.analysis.average.presenter;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.widget.ListView;

import com.zlzhang.stockanalysis.analysis.average.model.AverageInteractorImp;
import com.zlzhang.stockanalysis.analysis.average.model.IAverageInteractor;
import com.zlzhang.stockanalysis.analysis.average.view.AverageAdapter;
import com.zlzhang.stockanalysis.analysis.average.view.IAverageView;
import com.zlzhang.stockanalysis.views.ChooseDayView;
import com.zlzhang.stockmodel.AverageModel;
import com.zlzhang.stockmodel.StockModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by zhangzhilai on 2018/3/6.
 */

public class AveragePresenterImp implements IAveragePresenter {

    private final int CONTINUE_RISE_GOT = 0x01;

    private IAverageView mAverageView;
    private IAverageInteractor mAverageInteractor;
    private ListView mAverageListView;
    private AverageAdapter mAverageAdapter;
    private ChooseDayView mChooseDayView;
    private Context mContext;
    private Handler mHandler;

    private List<AverageModel> mContinueRiseModels;

    public AveragePresenterImp(IAverageView averageView, Context context){
        mAverageView = averageView;
        mAverageInteractor = new AverageInteractorImp();
        mContext = context;
        mChooseDayView = mAverageView.getChooseDayView();
        mAverageListView = mAverageView.getAverageListView();
        mAverageAdapter = new AverageAdapter(mContext);
        mAverageListView.setAdapter(mAverageAdapter);
        initHandler();
        initListener();
    }

    private void initListener() {
        mChooseDayView.setOnChooseDayViewListener(new ChooseDayView.OnChooseDayViewListener() {
            @Override
            public void onDayChoose(int day) {
                getContinueRiseStocks(day);
            }
        });
    }

    private void initHandler() {
        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what){
                    case  CONTINUE_RISE_GOT:
                        mAverageAdapter.setData(mContinueRiseModels);
                        break;
                }
            }
        };
    }

    @Override
    public void getContinueRiseStocks(int days) {
        mAverageInteractor.getAllStocksByTime(days, new IAverageInteractor.OnStockListener() {
            @Override
            public void onStocksGot(List<AverageModel> stockModels) {
                mContinueRiseModels = stockModels;
                if (mContinueRiseModels.size() > 0) {
                    Message message = new Message();
                    message.what = CONTINUE_RISE_GOT;
                    mHandler.sendMessage(message);
                }
            }
        });
    }

}
