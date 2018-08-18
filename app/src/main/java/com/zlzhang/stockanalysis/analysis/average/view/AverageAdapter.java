package com.zlzhang.stockanalysis.analysis.average.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zlzhang.stockanalysis.R;
import com.zlzhang.stockmodel.AverageModel;

import java.util.List;

/**
 * Created by zhangzhilai on 2018/8/9.
 */

public class AverageAdapter extends BaseAdapter {

    private Context mContext;
    private List<AverageModel> mAverageModels;

    public AverageAdapter(Context context){
        mContext = context;
    }

    public void setData(List<AverageModel> continueRiseModelList){
        mAverageModels = continueRiseModelList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        if (mAverageModels != null) {
            return mAverageModels.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int i) {
        if (mAverageModels != null) {
            return mAverageModels.get(i);
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
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_average, null);
            holder.codeTextView= (TextView) convertView.findViewById(R.id.code_textview);
            holder.nameTextView = (TextView) convertView.findViewById(R.id.name_textview);
            holder.pricesTextView = (TextView) convertView.findViewById(R.id.prices_textview);
            holder.riseDaysTextView = (TextView) convertView.findViewById(R.id.rise_days_textview);
            holder.averageTextView = (TextView) convertView.findViewById(R.id.average_textview);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        AverageModel averageModel = mAverageModels.get(i);
        holder.codeTextView.setText(averageModel.getCode());
        holder.nameTextView.setText(averageModel.getName());
        List<Float> prices = averageModel.getPrices();
        String priceString = "";
        for (Float price : prices) {
            priceString += price + ",";
        }
        holder.pricesTextView.setText(priceString);
        holder.riseDaysTextView.setText(averageModel.getDays() + "");
        holder.averageTextView.setText(averageModel.getAveragePrice() + "");
        return convertView;
    }

    public class ViewHolder{
        TextView codeTextView;
        TextView nameTextView;
        TextView pricesTextView;
        TextView riseDaysTextView;
        TextView averageTextView;
    }
}
