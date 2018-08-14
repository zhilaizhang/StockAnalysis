package com.zlzhang.stockanalysis.list.presenter;

import android.widget.ListView;

import com.zlzhang.stockmodel.StockType;


/**
 * Created by zhangzhilai on 2018/3/6.
 */

public interface IStockPresenter {

    void setListView(ListView listView);

    void setStockType(StockType stockType);

}
