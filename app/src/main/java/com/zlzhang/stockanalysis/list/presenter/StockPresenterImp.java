package com.zlzhang.stockanalysis.list.presenter;

import android.content.Context;
import android.os.Handler;
import android.widget.ListView;

import com.zlzhang.stockanalysis.list.model.IStockInteractor;
import com.zlzhang.stockanalysis.list.model.StockInteractorImp;
import com.zlzhang.stockanalysis.list.model.StockListAdapter;
import com.zlzhang.stockanalysis.list.view.IStockListView;
import com.zlzhang.stockmodel.StockDataCache;
import com.zlzhang.stockmodel.StockModel;
import com.zlzhang.stockmodel.StockType;

import java.util.List;
/**
 * Created by zhangzhilai on 2018/3/6.
 */

public class StockPresenterImp implements IStockPresenter {

    private Context mContext;
    private IStockListView mSHView;
    private Handler mHandler;

    private IStockInteractor mStockInteractor;

    private ListView mStockListView;
    private StockListAdapter mStockListAdapter;

    public StockPresenterImp(Context context, IStockListView shView){
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
    }

    @Override
    public void setStockType(StockType stockType) {
        List<StockModel> stockModels;
        if (stockType == StockType.SH) {
            stockModels = StockDataCache.getInstance().getmSHStockModelList();
        } else {
            stockModels = StockDataCache.getInstance().getmSZStockModelList();
        }
        mStockListAdapter.setData(stockModels);
    }

}
