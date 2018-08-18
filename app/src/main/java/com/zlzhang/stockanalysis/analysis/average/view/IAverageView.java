package com.zlzhang.stockanalysis.analysis.average.view;

import android.widget.ListView;

import com.zlzhang.stockanalysis.views.ChooseDayView;

/**
 * Created by zhangzhilai on 2018/3/6.
 */

public interface IAverageView {
     ListView getAverageListView();

     ChooseDayView getChooseDayView();
}
