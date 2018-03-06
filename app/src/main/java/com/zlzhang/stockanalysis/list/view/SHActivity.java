package com.zlzhang.stockanalysis.list.view;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.zlzhang.stockanalysis.R;
import com.zlzhang.stockanalysis.list.presenter.ISHPresenter;
import com.zlzhang.stockanalysis.list.presenter.SHPresenterImp;


/**
 * Created by zhangzhilai on 2018/3/6.
 */

public class SHActivity extends Activity implements ISHView {

    private ISHPresenter mSHPresenter;
    private ListView mSHListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sh);
        initPresenter();
        initView();
    }

    private void initView() {
        mSHListView = findViewById(R.id.sh_listview);
        mSHPresenter.setListView(mSHListView);
    }

    private void initPresenter() {
        mSHPresenter = new SHPresenterImp(this, this);
    }


}
