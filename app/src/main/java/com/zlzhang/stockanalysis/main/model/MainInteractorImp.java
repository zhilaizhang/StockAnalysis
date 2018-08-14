package com.zlzhang.stockanalysis.main.model;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zlzhang.stockanalysis.StockAnalysisUtil;
import com.zlzhang.stockmodel.GlobalVariable;
import com.zlzhang.stockmodel.StockDataCache;
import com.zlzhang.stockmodel.StockModel;
import com.zlzhang.util.FileUtil;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by zhangzhilai on 2018/3/13.
 */

public class MainInteractorImp implements IMainInteractor {

    private Context mContext;
    private Gson mGson;
    private int mCount;
    private OnGetDataListener mOnGetDataListener;

    public MainInteractorImp(Context context){
        mContext = context;
        mGson = new Gson();
    }

    @Override
    public void getAllStocks(final OnGetDataListener onGetDataListener) {
        mOnGetDataListener = onGetDataListener;
        final String sh = StockAnalysisUtil.readAssetsTxt(mContext, "sh");
        final String[] shStockNames = sh.split(",");

        String sz = StockAnalysisUtil.readAssetsTxt(mContext, "sz");
        final String[] szStockNames = sz.split(",");


        new Thread(new Runnable() {
            @Override
            public void run() {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH)+1;
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                String ds = year + "_" + month + "_" + day;
                String shFile = GlobalVariable.BASE_PATH +  ds + "_sh.txt";
                List<StockModel> shStocks;
                if (FileUtil.isFileExist(shFile)) {
                    shStocks = getStockModelsFromLocal(shFile);
                } else {
                    shStocks = getStockModelsFromNet("sh", shStockNames);
                    storeStocksToLocal(shFile, shStocks);
                }
                StockDataCache.getInstance().setmSHStockModelList(shStocks);

                List<StockModel> szStocks;
                String szFile = GlobalVariable.BASE_PATH +  ds + "_sz.txt";
                if (FileUtil.isFileExist(szFile)) {
                    szStocks = getStockModelsFromLocal(szFile);
                } else {
                    szStocks = getStockModelsFromNet("sz", szStockNames);
                    storeStocksToLocal(szFile, szStocks);
                }
                StockDataCache.getInstance().setmSZStockModelList(szStocks);

                List<StockModel> allStocks = new ArrayList<>();
                allStocks.addAll(shStocks);
                allStocks.addAll(szStocks);
                onGetDataListener.onGetSucceed(allStocks);

            }
        }).start();

    }

    private void storeStocksToLocal(String fileName, List<StockModel> stockModels){
        Gson gson = new Gson();
        String stockString = gson.toJson(stockModels);
        FileUtil.setRawDataToFile(fileName, stockString);
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

    private List<StockModel> getStockModelsFromNet(String position, String[] stocks){
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

    private List<StockModel> getStockModelsFromLocal(String path){
        String stocksFile = FileUtil.getAllFile(path);
        List<StockModel> stockModels = mGson.fromJson(stocksFile, new TypeToken<List<StockModel>>(){}.getType());
        return stockModels;
    }
}
