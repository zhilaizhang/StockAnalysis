package com.zlzhang.stockanalysis.list.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import com.zlzhang.stockanalysis.R;
import com.zlzhang.stockanalysis.StockAnalysisUtil;
import com.zlzhang.stockanalysis.list.presenter.IStockPresenter;
import com.zlzhang.stockanalysis.list.presenter.StockPresenterImp;
import com.zlzhang.stockanalysis.modle.StockType;


/**
 * Created by zhangzhilai on 2018/3/6.
 */

public class StockListActivity extends Activity implements IStockListView {

    private IStockPresenter mSHPresenter;
    private ListView mSHListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sh);
        initPresenter();
        initView();
        initData();
    }

    private void initData() {
        Intent intent = getIntent();
        StockType stockType = (StockType) intent.getSerializableExtra(StockAnalysisUtil.STOCK_TYPE);
        mSHPresenter.setStockType(stockType);
    }

    private void initView() {
        mSHListView = (ListView) findViewById(R.id.sh_listview);
        mSHPresenter.setListView(mSHListView);
    }

    private void initPresenter() {
        mSHPresenter = new StockPresenterImp(this, this);

    }


}
