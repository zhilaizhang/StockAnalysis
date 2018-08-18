package com.zlzhang.stockanalysis;

import com.zlzhang.stockmodel.StockModel;

import java.util.List;

/**
 * Created by zhangzhilai on 2018/8/18.
 * 布林线工具
 */

public class BollUtil {

    /**
     * N日平均线
     * @return
     */
    public static float getMA(List<StockModel> stockModels){
        if (stockModels == null) {
            return 0;
        }
        int days = stockModels.size();
        float sum = 0;
        for (StockModel stockModel : stockModels) {
            sum += stockModel.getNowPrice();
        }
        return sum / days;
    }

    /**
     * 计算上轨线
     * @return
     */
    public static float getUP(List<StockModel> stockModels){
        float MB = getMA(stockModels);
        float MD = getMD(stockModels);
        return MB + 2 * MD;
    }

    /**
     * 计算下轨线
     * @param stockModels
     * @return
     */
    public static float getDN(List<StockModel> stockModels){
        float MB = getMA(stockModels);
        float MD = getMD(stockModels);
        return MB + 2 * MD;
    }

    /**
     * 计算标准差
     * @return
     */
    public static float getMD(List<StockModel> stockModels){
        int days = stockModels.size();
        float MA = getMA(stockModels);
        float sum = 0;
        for (StockModel stockModel : stockModels) {
            sum += Math.pow(stockModel.getNowPrice(), 2);
        }
        return (float) Math.sqrt(sum / days);
    }
}
