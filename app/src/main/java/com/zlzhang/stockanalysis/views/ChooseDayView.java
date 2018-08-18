package com.zlzhang.stockanalysis.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.zlzhang.stockanalysis.R;

/**
 * Created by zhangzhilai on 2018/8/18.
 */

public class ChooseDayView extends RelativeLayout {

    private Context mContext;
    private ListView mDaysListView;
    private DayChooseAdapter mDayChooseAdapter;
    private OnChooseDayViewListener mOnChooseDayViewListener;

    private int mChooseDay = 1;

    public ChooseDayView(Context context) {
        this(context, null);
    }

    public ChooseDayView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ChooseDayView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initView();
    }

    public void setOnChooseDayViewListener(OnChooseDayViewListener onChooseDayViewListener){
        mOnChooseDayViewListener = onChooseDayViewListener;
    }

    private void initView() {
        LayoutInflater.from(mContext).inflate(R.layout.view_choose_day, this);
        mDaysListView = (ListView) findViewById(R.id.day_choose_listview);
        mDayChooseAdapter = new DayChooseAdapter(mContext);
        mDaysListView.setAdapter(mDayChooseAdapter);

        initListener();
    }

    private void initListener() {
        mDaysListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                mChooseDay = i + 1;
                mOnChooseDayViewListener.onDayChoose(mChooseDay);
            }
        });

    }

    public interface OnChooseDayViewListener{
        void onDayChoose(int day);
    }
}
