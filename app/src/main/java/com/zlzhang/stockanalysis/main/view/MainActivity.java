package com.zlzhang.stockanalysis.main.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.zlzhang.stockanalysis.R;
import com.zlzhang.stockanalysis.StockAnalysisUtil;
import com.zlzhang.stockanalysis.list.view.StockListActivity;
import com.zlzhang.stockanalysis.main.presenter.IMainPresenter;
import com.zlzhang.stockanalysis.main.presenter.MainPresenterImp;
import com.zlzhang.stockanalysis.modle.StockType;
import com.zlzhang.stockanalysis.upload.view.UploadActvity;

public class MainActivity extends Activity implements IMainView, View.OnClickListener{

    private IMainPresenter mMainPresenter;

    private LinearLayout mProgressLayout;
    private TextView mDataStatusTextView;
    private TextView mCountTextView;
    private Button mUploadButton;
    private Button mGotoSHButton;
    private Button mGotoSZButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initListener();
        initData();
    }

    private void initListener() {
        mGotoSZButton.setOnClickListener(this);
        mGotoSHButton.setOnClickListener(this);
        mUploadButton.setOnClickListener(this);
    }

    private void initView() {
        mProgressLayout = findViewById(R.id.progress_layout);
        mDataStatusTextView = findViewById(R.id.data_status);
        mCountTextView = findViewById(R.id.count_textview);
        mGotoSHButton   = findViewById(R.id.gotoSH);
        mGotoSZButton   = findViewById(R.id.gotoSZ);
        mUploadButton = findViewById(R.id.upload_data);
    }

    private void initData() {
        mMainPresenter = new MainPresenterImp(this, this);
    }


    public void gotoSH() {
        Intent intent = new Intent();
        intent.putExtra(StockAnalysisUtil.STOCK_TYPE, StockType.SH);
        intent.setClass(MainActivity.this, StockListActivity.class);
        startActivity(intent);
    }

    public void gotoSZ() {
        Intent intent = new Intent();
        intent.putExtra(StockAnalysisUtil.STOCK_TYPE, StockType.SZ);
        intent.setClass(MainActivity.this, StockListActivity.class);
        startActivity(intent);
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
        if (isGot) {
            mGotoSHButton.setVisibility(View.VISIBLE);
            mGotoSZButton.setVisibility(View.VISIBLE);
            mUploadButton.setVisibility(View.VISIBLE);
        }
        Toast.makeText(this,  "got" + isGot, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showCount(int count) {
        mCountTextView.setText("" + count);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.gotoSH:
                gotoSH();
                break;
            case R.id.gotoSZ:
                gotoSZ();
                break;
            case R.id.upload_data:
                gotoUploadData();
                break;

        }
    }

    public void gotoUploadData() {
        Intent uploadIntent = new Intent();
        uploadIntent.setClass(this, UploadActvity.class);
        startActivity(uploadIntent);
//        mMainPresenter.uploadAllData();
    }
}
