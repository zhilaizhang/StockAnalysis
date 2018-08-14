package com.zlzhang.stockanalysis.analysis.rise.presenter;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.zlzhang.stockanalysis.StockAnalysisUtil;
import com.zlzhang.stockanalysis.analysis.rise.model.ContinueRiseInteractorImp;
import com.zlzhang.stockanalysis.analysis.rise.model.IContinueRiseInteractor;
import com.zlzhang.stockanalysis.analysis.rise.view.ContinueRiseAdapter;
import com.zlzhang.stockanalysis.analysis.rise.view.DayChooseAdapter;
import com.zlzhang.stockanalysis.analysis.rise.view.IContinueRiseView;
import com.zlzhang.stockanalysis.modle.ContinueRiseModel;
import com.zlzhang.stockanalysis.modle.StockModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by zhangzhilai on 2018/3/6.
 */

public class ContinueRisePresenterImp implements IContinueRisePresenter{

    private final int CONTINUE_RISE_GOT = 0x01;

    private IContinueRiseView mContinueRiseView;
    private IContinueRiseInteractor mContinueRiseInteractor;
    private ListView mContinueRiseListView;
    private ListView mDayChooseListView;
    private ContinueRiseAdapter mContinueRiseAdapter;
    private DayChooseAdapter mDayChooseAdapter;
    private Context mContext;
    private Handler mHandler;

    private List<ContinueRiseModel> mContinueRiseModels;

    public ContinueRisePresenterImp(IContinueRiseView continueRiseView, Context context){
        mContinueRiseView = continueRiseView;
        mContinueRiseInteractor = new ContinueRiseInteractorImp();
        mContext = context;
        mContinueRiseListView = mContinueRiseView.getContinueListView();
        mDayChooseListView = mContinueRiseView.getDaysChooseListView();

        mContinueRiseAdapter = new ContinueRiseAdapter(mContext);
        mContinueRiseListView.setAdapter(mContinueRiseAdapter);

        mDayChooseAdapter = new DayChooseAdapter(mContext);
        mDayChooseListView.setAdapter(mDayChooseAdapter);

        initListener();

        initHandler();
    }

    private void initListener() {
        mContinueRiseListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });

        mDayChooseListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                getContinueRiseStocks(i + 1);
            }
        });
    }

    private void initHandler() {
        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what){
                    case  CONTINUE_RISE_GOT:
                        mContinueRiseAdapter.setData(mContinueRiseModels);
                        break;
                }
            }
        };
    }

    @Override
    public void getContinueRiseStocks(int days) {
        String startTime = "2018-07-01";
        String endTime = "2018-08-14";
        mContinueRiseInteractor.getAllStocksByTime(startTime, endTime, new IContinueRiseInteractor.OnStockListener() {
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
            StockModel stockModel = stockModels.get(0);
            sortStocks(stockModels);
            boolean isContinueRise = isContinueRise(stockModels);
            if (isContinueRise) {
              float riseRate =  getRiseRate(stockModels);
              ContinueRiseModel continueRiseModel = new ContinueRiseModel();
              continueRiseModel.setCode(code);
              continueRiseModel.setContinueDays(stockModels.size());
              continueRiseModel.setName(stockModel.getName());
              continueRiseModel.setRiseRate(riseRate);
              List<Float> prices =  getContinueRisePrices(stockModels);
              continueRiseModel.setPrices(prices);
                mContinueRiseModels.add(continueRiseModel);
            }
        }
        if (mContinueRiseModels.size() > 0) {
            sortStocksByRiseRate(mContinueRiseModels);
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
     * 对当前天数内股票进行时间排序
     * @param stockModels
     */
    private void sortStocksByRiseRate(List<ContinueRiseModel> stockModels){
        Collections.sort(stockModels, new Comparator<ContinueRiseModel>() {
            @Override
            public int compare(ContinueRiseModel stockModel, ContinueRiseModel t1) {
                if (stockModel.getRiseRate() < t1.getRiseRate()) {
                    return 1;
                } else if(stockModel.getRiseRate() > t1.getRiseRate()){
                    return -1;
                }
                return 0;
            }
        });
    }

    /**
     * 判断在当前的天数内，是否为连涨
     * @param stockModels
     * @return
     */
    private boolean isContinueRise(List<StockModel> stockModels){
        float lastPrice = 0;
        for (StockModel stockModel : stockModels) {
            if (stockModel.getNowPrice() > lastPrice) {
                lastPrice = stockModel.getNowPrice();
            } else {
                return false;
            }
        }
        return true;
    }

    /**
     * 计算涨幅
     * @param stockModels
     * @return
     */
    private float getRiseRate(List<StockModel> stockModels){
        int length = stockModels.size();
        float startPrice = stockModels.get(0).getYesterdayClose();
        float endPrice = stockModels.get(length -1).getNowPrice();
        float riseRate = (endPrice - startPrice) / startPrice * 100;
        int rise = (int) (riseRate * 100);
        return (float) (rise * 1.0 / 100);
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
