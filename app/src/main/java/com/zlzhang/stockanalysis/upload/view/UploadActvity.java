package com.zlzhang.stockanalysis.upload.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;


import com.zlzhang.stockanalysis.R;
import com.zlzhang.stockanalysis.upload.presenter.IUploadPresenter;
import com.zlzhang.stockanalysis.upload.presenter.UploadPresenterImp;

import java.util.List;

/**
 * Created by zhangzhilai on 2018/7/29.
 */

public class UploadActvity extends Activity implements IUploadView{

    private IUploadPresenter mUploadPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);
        initData();
    }

    private void initData() {
        mUploadPresenter = new UploadPresenterImp(this, this);
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

    public void uploadData(View view) {
        mUploadPresenter.uploadStocks();
    }

    public void uploadInfo(View view) {
        mUploadPresenter.uploadStockInfo();

    }

//    public void uploadData(View view) {
//        mUploadPresenter.uploadStocks();
//    }
}
