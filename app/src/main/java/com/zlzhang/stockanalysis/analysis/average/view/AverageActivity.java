package com.zlzhang.stockanalysis.analysis.average.view;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.zlzhang.stockanalysis.R;
import com.zlzhang.stockanalysis.analysis.average.presenter.AveragePresenterImp;
import com.zlzhang.stockanalysis.analysis.average.presenter.IAveragePresenter;
import com.zlzhang.stockanalysis.views.ChooseDayView;

/**
 * Created by zhangzhilai on 2018/3/6.
 */

public class AverageActivity extends Activity implements IAverageView {

    private ListView mAverageListView;
    private ChooseDayView mChooseDayView;
    private IAveragePresenter mAveragePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_average);
        initView();
        mAveragePresenter = new AveragePresenterImp(this, this);
    }

    private void initView() {
        mAverageListView = (ListView) findViewById(R.id.average_listview);
        mChooseDayView = (ChooseDayView) findViewById(R.id.choose_day_view);
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
    public ListView getAverageListView() {
        return mAverageListView;
    }

    @Override
    public ChooseDayView getChooseDayView() {
        return mChooseDayView;
    }
}
