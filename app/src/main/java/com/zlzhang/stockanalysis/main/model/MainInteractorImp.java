package com.zlzhang.stockanalysis.main.model;

import android.content.Context;

import com.google.gson.Gson;
import com.zlzhang.stockanalysis.StockAnalysisUtil;
import com.zlzhang.stockanalysis.modle.GlobalVariable;
import com.zlzhang.stockanalysis.modle.StockDataCache;
import com.zlzhang.stockanalysis.modle.StockModel;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by zhangzhilai on 2018/3/13.
 */

public class MainInteractorImp implements IMainInteractor {

    private Context mContext;
    private int mCount;
    private OnGetDataListener mOnGetDataListener;

    public MainInteractorImp(Context context){
        mContext = context;
    }

    @Override
    public void getAllStocks(final OnGetDataListener onGetDataListener) {
        mOnGetDataListener = onGetDataListener;
        String sh = StockAnalysisUtil.readAssetsTxt(mContext, "sh");
        final String[] shStockNames = sh.split(",");

        String sz = StockAnalysisUtil.readAssetsTxt(mContext, "sz");
        final String[] szStockNames = sz.split(",");


        new Thread(new Runnable() {
            @Override
            public void run() {
                List<StockModel> shStocks = getStockModels("sh", shStockNames);
                StockDataCache.getInstance().setmSHStockModelList(shStocks);

                List<StockModel> szStocks = getStockModels("sz", szStockNames);
                StockDataCache.getInstance().setmSZStockModelList(szStocks);

                List<StockModel> allStocks = new ArrayList<>();
                allStocks.addAll(shStocks);
                allStocks.addAll(szStocks);
                storeAllStocks(allStocks);
                onGetDataListener.onGetSucceed(allStocks);

            }
        }).start();

    }

    private void storeAllStocks(List<StockModel> stockModels){
        Gson gson = new Gson();
        String stockString = gson.toJson(stockModels);
        String date = new Date().toString();
        String file = GlobalVariable.BASE_PATH + date;
        writeToFile(file, stockString);
    }

    public  void writeToFile(String file, String conent) {
        BufferedWriter out = null;
        try {
            out = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(file, true)));
            out.write(conent);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void uploadStocks(List<StockModel> stockModels, OnUploadListener onUploadListener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                //TODO 上传
            }
        }).start();
    }

    private List<StockModel> getStockModels(String position, String[] stocks){
        List<StockModel> stockModelList = new ArrayList<>();
        for (String shStock : stocks) {
            String url = "http://hq.sinajs.cn/list=" + position + shStock;
            String result =  StockAnalysisUtil.getHttpURLConnection(url, "");
            if (result == null) {
                continue;
            }
            StockModel stockModel = StockAnalysisUtil.changeToModel(result);
            if (stockModel != null) {
                mCount++;
                stockModelList.add(stockModel);
                mOnGetDataListener.onProgress(mCount);
            }
        }
        return stockModelList;
    }
}
