package com.zlzhang.stockanalysis.list.presenter;

import android.content.Context;
import android.os.Handler;
import android.widget.Adapter;
import android.widget.ListView;

import com.zlzhang.stockanalysis.list.model.IStockInteractor;
import com.zlzhang.stockanalysis.list.model.StockInteractorImp;
import com.zlzhang.stockanalysis.list.model.StockListAdapter;
import com.zlzhang.stockanalysis.list.view.ISHView;
import com.zlzhang.stockanalysis.modle.StockModel;
import com.zlzhang.stockanalysis.modle.StockType;

import java.util.List;
/**
 * Created by zhangzhilai on 2018/3/6.
 */

public class SHPresenterImp implements ISHPresenter {

    private Context mContext;
    private ISHView mSHView;
    private Handler mHandler;

    private IStockInteractor mStockInteractor;

    private ListView mStockListView;
    private StockListAdapter mStockListAdapter;

    public SHPresenterImp(Context context, ISHView shView){
        mContext = context;
        mSHView = shView;
        mStockInteractor = new StockInteractorImp();
        mHandler = new Handler();
    }

    @Override
    public void setListView(ListView listView) {
        mStockListView = listView;
        mStockListAdapter = new StockListAdapter(mContext);
        mStockListView.setAdapter(mStockListAdapter);
        mStockInteractor.getStockList(mContext, StockType.SH, new IStockInteractor.OnStockListener() {
            @Override
            public void onStockGot(final List<StockModel> stockModels) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mStockListAdapter.setData(stockModels);
                    }
                });
            }
        });
    }


}
