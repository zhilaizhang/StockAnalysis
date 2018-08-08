package com.zlzhang.stockanalysis.upload.view;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.zlzhang.stockanalysis.R;
import com.zlzhang.stockanalysis.upload.presenter.IUploadPresenter;
import com.zlzhang.stockanalysis.upload.presenter.UploadPresenterImp;

import java.util.List;

/**
 * Created by zhangzhilai on 2018/7/29.
 */

public class UploadActvity extends Activity implements IUploadView{

    private Context mContext;
    private IUploadPresenter mUploadPresenter;
    private TextView mUploadStatusEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);
        mContext = this;
        initView();
        initData();
    }

    private void initView() {
        mUploadStatusEditText = findViewById(R.id.upload_status_textview);
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

    @Override
    public void uploadHint(String hint) {
        Toast.makeText(mContext, hint, Toast.LENGTH_SHORT).show();
        mUploadStatusEditText.setText(hint);
    }

//    public void uploadData(View view) {
//        mUploadPresenter.uploadStocks();
//    }
}
