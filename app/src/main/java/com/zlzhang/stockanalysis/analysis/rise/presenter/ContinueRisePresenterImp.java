package com.zlzhang.stockanalysis.analysis.rise.presenter;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.zlzhang.stockanalysis.analysis.rise.model.ContinueRiseInteractorImp;
import com.zlzhang.stockanalysis.analysis.rise.model.IContinueRiseInteractor;
import com.zlzhang.stockanalysis.analysis.rise.view.ContinueRiseAdapter;
import com.zlzhang.stockanalysis.analysis.rise.view.DayChooseAdapter;
import com.zlzhang.stockanalysis.analysis.rise.view.IContinueRiseView;
import com.zlzhang.stockanalysis.views.ChooseDayView;
import com.zlzhang.stockmodel.ContinueRiseModel;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by zhangzhilai on 2018/3/6.
 */

public class ContinueRisePresenterImp implements IContinueRisePresenter{

    private final int CONTINUE_RISE_GOT = 0x01;

    private IContinueRiseView mContinueRiseView;
    private IContinueRiseInteractor mContinueRiseInteractor;
    private ListView mContinueRiseListView;
    private ContinueRiseAdapter mContinueRiseAdapter;

    private ChooseDayView mChooseDayView;

    private Context mContext;
    private Handler mHandler;

    private List<ContinueRiseModel> mContinueRiseModels;

    public ContinueRisePresenterImp(IContinueRiseView continueRiseView, Context context){
        mContinueRiseView = continueRiseView;
        mContinueRiseInteractor = new ContinueRiseInteractorImp();
        mContext = context;
        mContinueRiseListView = mContinueRiseView.getContinueListView();
        mChooseDayView = mContinueRiseView.getChooseDayView();

        mContinueRiseAdapter = new ContinueRiseAdapter(mContext);
        mContinueRiseListView.setAdapter(mContinueRiseAdapter);


        initListener();

        initHandler();
    }

    private void initListener() {
        mContinueRiseListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });

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
                        mContinueRiseAdapter.setData(mContinueRiseModels);
                        break;
                }
            }
        };
    }

    @Override
    public void getContinueRiseStocks(int days) {
        mContinueRiseInteractor.getAllStocksByTime(days, new IContinueRiseInteractor.OnStockListener() {
            @Override
            public void onStocksGot(List<ContinueRiseModel> stockModels) {
                if (stockModels != null && stockModels.size() > 0) {
                    mContinueRiseModels = stockModels;
                    sortStocksByRiseRate(mContinueRiseModels);
                    Message message = new Message();
                    message.what = CONTINUE_RISE_GOT;
                    mHandler.sendMessage(message);
                }
            }
        });
    }


    /**
     * 对当前天数内股票进行时间排序
     * @param stockModels
     */
    private void sortStocksByRiseRate(List<ContinueRiseModel> stockModels){
        Collections.sort(stockModels, new Comparator<ContinueRiseModel>() {
            @Override
            public int compare(ContinueRiseModel stockModel, ContinueRiseModel t1) {
                if (stockModel.getRiseRate() < t1.getRiseRate()) {
                    return 1;
                } else if(stockModel.getRiseRate() > t1.getRiseRate()){
                    return -1;
                }
                return 0;
            }
        });
    }

}
