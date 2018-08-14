package com.zlzhang.stockanalysis.analysis.rise.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zlzhang.stockanalysis.R;
import com.zlzhang.stockanalysis.modle.ContinueRiseModel;

import java.util.List;

/**
 * Created by zhangzhilai on 2018/8/9.
 */

public class ContinueRiseAdapter extends BaseAdapter {

    private Context mContext;
    private List<ContinueRiseModel> mContinueRiseModels;

    public ContinueRiseAdapter(Context context){
        mContext = context;
    }

    public void setData(List<ContinueRiseModel> continueRiseModelList){
        mContinueRiseModels = continueRiseModelList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        if (mContinueRiseModels != null) {
            return mContinueRiseModels.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int i) {
        if (mContinueRiseModels != null) {
            return mContinueRiseModels.get(i);
        }
        return null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_continue_rise, null);
            holder.codeTextView= (TextView) convertView.findViewById(R.id.code_textview);
            holder.nameTextView = (TextView) convertView.findViewById(R.id.name_textview);
            holder.pricesTextView = (TextView) convertView.findViewById(R.id.prices_textview);
            holder.riseDaysTextView = (TextView) convertView.findViewById(R.id.rise_days_textview);
            holder.riseRateTextView = (TextView) convertView.findViewById(R.id.rise_rate_textview);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        ContinueRiseModel continueRiseModel = mContinueRiseModels.get(i);
        holder.codeTextView.setText(continueRiseModel.getCode());
        holder.nameTextView.setText(continueRiseModel.getName());
        List<Float> prices = continueRiseModel.getPrices();
        String priceString = "";
        for (Float price : prices) {
            priceString = price + ",";
        }
        holder.pricesTextView.setText(priceString);
        holder.riseDaysTextView.setText(continueRiseModel.getContinueDays() + "");
        holder.riseRateTextView.setText(continueRiseModel.getRiseRate() + "%");
        return convertView;
    }

    public class ViewHolder{
        TextView codeTextView;
        TextView nameTextView;
        TextView pricesTextView;
        TextView riseDaysTextView;
        TextView riseRateTextView;
    }
}
