package com.zlzhang.stockanalysis.analysis.view;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.zlzhang.stockanalysis.R;
import com.zlzhang.stockanalysis.analysis.presenter.ContinueRisePresenterImp;
import com.zlzhang.stockanalysis.analysis.presenter.IContinueRisePresenter;

/**
 * Created by zhangzhilai on 2018/3/6.
 */

public class ContinueRiseActivity extends Activity implements IContinueRiseView {

    private ListView mContinueListView;
    private IContinueRisePresenter mContinueRisePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_continue_rise);
        initView();
        mContinueRisePresenter = new ContinueRisePresenterImp(this, this);
    }

    private void initView() {
        mContinueListView = (ListView) findViewById(R.id.continue_rise_listview);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public ListView getContinueListView() {
        return mContinueListView;
    }
}
