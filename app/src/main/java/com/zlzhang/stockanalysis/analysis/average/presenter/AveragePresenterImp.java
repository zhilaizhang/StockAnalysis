package com.zlzhang.stockanalysis.analysis.average.presenter;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.widget.ListView;

import com.zlzhang.stockanalysis.analysis.average.model.AverageInteractorImp;
import com.zlzhang.stockanalysis.analysis.average.model.IAverageInteractor;
import com.zlzhang.stockanalysis.analysis.average.view.AverageAdapter;
import com.zlzhang.stockanalysis.analysis.average.view.IAverageView;
import com.zlzhang.stockmodel.AverageModel;
import com.zlzhang.stockmodel.StockModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by zhangzhilai on 2018/3/6.
 */

public class AveragePresenterImp implements IAveragePresenter {

    private final int CONTINUE_RISE_GOT = 0x01;

    private IAverageView mAverageView;
    private IAverageInteractor mAverageInteractor;
    private ListView mAverageListView;
    private AverageAdapter mAverageAdapter;
    private Context mContext;
    private Handler mHandler;

    private List<AverageModel> mContinueRiseModels;

    public AveragePresenterImp(IAverageView averageView, Context context){
        mAverageView = averageView;
        mAverageInteractor = new AverageInteractorImp();
        mContext = context;
        mAverageListView = mAverageView.getAverageListView();
        mAverageAdapter = new AverageAdapter(mContext);
        mAverageListView.setAdapter(mAverageAdapter);
        initHandler();
    }

    private void initHandler() {
        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what){
                    case  CONTINUE_RISE_GOT:
                        mAverageAdapter.setData(mContinueRiseModels);
                        break;
                }
            }
        };
    }

    @Override
    public void getContinueRiseStocks(int days) {
        mAverageInteractor.getAllStocksByTime("", "", new IAverageInteractor.OnStockListener() {
            @Override
            public void onStocksGot(Map<String, List<StockModel>> stockModels) {
                calculateContinueRiseStock(stockModels);
            }
        });
    }

    private void calculateContinueRiseStock(Map<String, List<StockModel>> stockModelMap) {
        Iterator map1it = stockModelMap.entrySet().iterator();
        mContinueRiseModels = new ArrayList<>();
        while (map1it.hasNext()){
            Map.Entry entry = (Map.Entry) map1it.next();
            String code = (String) entry.getKey();
            List<StockModel> stockModels = (List<StockModel>) entry.getValue();
            int days = stockModels.size();
            StockModel stockModel = stockModels.get(0);
            sortStocks(stockModels);
            float averagePrice = getAverage(stockModels);
            boolean isUpAverage = isUpAverage(stockModels.get(days - 1), averagePrice);
            if (isUpAverage) {
              float riseRate =  getRiseRate(stockModels);
                AverageModel averageModel = new AverageModel();
                averageModel.setCode(code);
                averageModel.setDays(stockModels.size());
                averageModel.setName(stockModel.getName());
                averageModel.setAveragePrice(averagePrice);
              List<Float> prices =  getContinueRisePrices(stockModels);
                averageModel.setPrices(prices);
                mContinueRiseModels.add(averageModel);
            }
        }
        if (mContinueRiseModels.size() > 0) {
            Message message = new Message();
            message.what = CONTINUE_RISE_GOT;
            mHandler.sendMessage(message);
        }
    }


    /**
     * 对当前天数内股票进行时间排序
     * @param stockModels
     */
    private void sortStocks(List<StockModel> stockModels){
        Collections.sort(stockModels, new Comparator<StockModel>() {
            @Override
            public int compare(StockModel stockModel, StockModel t1) {
                if (stockModel.getId() > t1.getId()) {
                    return 1;
                } else if(stockModel.getId() < t1.getId()){
                    return -1;
                }
                return 0;
            }
        });
    }

    /**
     * 计算均线
     * @return
     */
    private float getAverage(List<StockModel> stockModels){
        int size = stockModels.size();
        float sum = 0;
        for (StockModel stockModel : stockModels) {
            sum += stockModel.getNowPrice();
        }
        return sum / size;
    }

    /**
     *  是否站上均线
     * @param lastStockModel
     * @param averagePrice
     * @return
     */
    private boolean isUpAverage(StockModel lastStockModel, float averagePrice){
        if (lastStockModel.getNowPrice() > averagePrice) {
            return true;
        }
        return false;
    }


    /**
     * 计算涨幅
     * @param stockModels
     * @return
     */
    private float getRiseRate(List<StockModel> stockModels){
        int length = stockModels.size();
        float startPrice = stockModels.get(0).getTodayOpen();
        float endPrice = stockModels.get(length -1).getNowPrice();
        float riseRate = (endPrice - startPrice) / startPrice * 100;
        return riseRate;
    }

    /**
     * 获取连涨天数内每天的收盘价
     * @param stockModels
     * @return
     */
    private List<Float> getContinueRisePrices(List<StockModel> stockModels){
        List<Float> prices = new ArrayList<>();
        for (StockModel stockModel : stockModels) {
            prices.add(stockModel.getNowPrice());
        }
        return prices;
    }

}
