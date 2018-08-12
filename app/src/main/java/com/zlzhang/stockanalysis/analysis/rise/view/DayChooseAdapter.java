package com.zlzhang.stockanalysis.analysis.rise.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zlzhang.stockanalysis.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangzhilai on 2018/8/12.
 */

public class DayChooseAdapter extends BaseAdapter {

    private List<Integer> mDays;
    private Context mContext;

    public DayChooseAdapter(Context context){
        mContext = context;
        initDays();
    }

    private void initDays() {
        mDays = new ArrayList<>();
        for (int i = 1; i < 30; i++) {
            mDays.add(i);
        }

    }

    @Override
    public int getCount() {
        return mDays.size();
    }

    @Override
    public Object getItem(int i) {
        return mDays.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_days_choose, null);
        }
        TextView textView = (TextView) convertView.findViewById(R.id.day_textview);
        textView.setText("" + mDays.get(i));
        return convertView;
    }
}
