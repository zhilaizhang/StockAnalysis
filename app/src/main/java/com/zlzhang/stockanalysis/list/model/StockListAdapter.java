package com.zlzhang.stockanalysis.list.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zlzhang.stockanalysis.R;
import com.zlzhang.stockanalysis.modle.StockModel;

import java.util.List;

/**
 * Created by zhangzhilai on 2018/3/6.
 */

public class StockListAdapter extends BaseAdapter {

    private Context mContext;
    private List<StockModel> mStockModelList;

    public StockListAdapter(Context context){
        mContext = context;
    }

    public void setData(List<StockModel> modelList){
        mStockModelList = modelList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        if (mStockModelList != null) {
            return mStockModelList.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        if (mStockModelList != null) {
            mStockModelList.get(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null){
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_stock_list, null);
            viewHolder.stockNameTextView = (TextView) convertView.findViewById(R.id.stock_name_textview);
            viewHolder.stockCodeTextView = (TextView) convertView.findViewById(R.id.stock_code_textview);
            viewHolder.stockPriceTextView = (TextView) convertView.findViewById(R.id.stock_price_textview);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        StockModel stockModel = mStockModelList.get(position);
        viewHolder.stockNameTextView.setText(stockModel.getName());
        viewHolder.stockCodeTextView.setText(stockModel.getCode());
        viewHolder.stockPriceTextView.setText(stockModel.getNowPrice() + "");
        return convertView;
    }

    class ViewHolder{
        TextView stockNameTextView;
        TextView stockCodeTextView;
        TextView stockPriceTextView;
    }
}
