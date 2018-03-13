package com.zlzhang.stockanalysis.list.model;

import android.content.Context;

import com.zlzhang.stockanalysis.StockAnalysisUtil;
import com.zlzhang.stockanalysis.modle.StockModel;
import com.zlzhang.stockanalysis.modle.StockType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangzhilai on 2018/3/6.
 */

public class StockInteractorImp implements IStockInteractor {


    public void getStockList(Context context, StockType type, final OnStockListener onStockListener){
        final String sh = StockAnalysisUtil.readAssetsTxt(context, "sh");
        final String[] shStocks = sh.split(",");

        new Thread(new Runnable() {
            @Override
            public void run() {
                List<StockModel> stockModelList = new ArrayList<>();
                for (String shStock : shStocks) {
                    String url = "http://hq.sinajs.cn/list=sh" + shStock;
                    String result =  StockAnalysisUtil.getHttpURLConnection(url, "");
                    if (result == null) {
                        continue;
                    }
                    StockModel stockModel = StockAnalysisUtil.changeToModel(result);
                    if (stockModel != null) {
                        stockModelList.add(stockModel);
                    }
                }
                onStockListener.onStockGot(stockModelList);
            }
        }).start();
    }


}
