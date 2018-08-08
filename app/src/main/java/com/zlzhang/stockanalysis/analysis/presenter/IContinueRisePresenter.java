package com.zlzhang.stockanalysis.list.presenter;

import android.widget.ListView;

import com.zlzhang.stockanalysis.modle.StockType;

/**
 * Created by zhangzhilai on 2018/3/6.
 */

public interface IContinueRisePresenter {

    void setListView(ListView listView);

    void setStockType(StockType stockType);

}
