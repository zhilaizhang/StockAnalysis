package com.zlzhang.stockanalysis.analysis.presenter;

import android.content.Context;
import android.widget.ListView;

import com.zlzhang.stockanalysis.analysis.model.ContinueRiseInteractorImp;
import com.zlzhang.stockanalysis.analysis.model.IContinueRiseInteractor;
import com.zlzhang.stockanalysis.analysis.view.ContinueRiseAdapter;
import com.zlzhang.stockanalysis.analysis.view.IContinueRiseView;
import com.zlzhang.stockanalysis.modle.StockModel;

import java.util.List;
import java.util.Map;

/**
 * Created by zhangzhilai on 2018/3/6.
 */

public class ContinueRisePresenterImp implements IContinueRisePresenter{

    private IContinueRiseView mContinueRiseView;
    private IContinueRiseInteractor mContinueRiseInteractor;
    private ListView mContinueRiseListView;
    private ContinueRiseAdapter mContinueRiseAdapter;
    private Context mContext;

    public ContinueRisePresenterImp(IContinueRiseView continueRiseView, Context context){
        mContinueRiseView = continueRiseView;
        mContinueRiseInteractor = new ContinueRiseInteractorImp();
        mContext = context;
        mContinueRiseListView = mContinueRiseView.getContinueListView();
        mContinueRiseAdapter = new ContinueRiseAdapter(mContext);
        mContinueRiseListView.setAdapter(mContinueRiseAdapter);
    }

    @Override
    public void getContinueRiseStocks(int days) {
        mContinueRiseInteractor.getAllStocksByTime("", "", new IContinueRiseInteractor.OnStockListener() {
            @Override
            public void onStocksGot(Map<String, List<StockModel>> stockModels) {
                //TODO 计算
                //赋值
            }
        });
    }

}
