package com.zlzhang.stockanalysis.main.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.zlzhang.stockanalysis.R;
import com.zlzhang.stockanalysis.list.view.SHActivity;
import com.zlzhang.stockanalysis.main.presenter.IMainPresenter;
import com.zlzhang.stockanalysis.main.presenter.MainPresenterImp;

public class MainActivity extends Activity implements IMainView{

    private IMainPresenter mMainPresenter;

    private LinearLayout mProgressLayout;
    private TextView mDataStatusTextView;
    private TextView mCountTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initView() {
        mProgressLayout = findViewById(R.id.progress_layout);
        mDataStatusTextView = findViewById(R.id.data_status);
        mCountTextView = findViewById(R.id.count_textview);
    }

    private void initData() {
        mMainPresenter = new MainPresenterImp(this, this);
    }


    public void gotoSH(View view) {
        Intent intent = new Intent();
        intent.setClass(MainActivity.this, SHActivity.class);
        startActivity(intent);
    }

    public void gotoSZ(View view) {

    }

    public void getData(View view) {
        mMainPresenter.getAllData();
    }

    @Override
    public void showLoadingView(boolean isShow) {
        if (isShow) {
            mProgressLayout.setVisibility(View.VISIBLE);
        } else {
            mProgressLayout.setVisibility(View.GONE);
        }
    }

    @Override
    public void dataGot(boolean isGot) {
        mDataStatusTextView.setText("" + isGot);
        Toast.makeText(this,  "got" + isGot, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showCount(int count) {
        mCountTextView.setText("" + count);
    }
}
