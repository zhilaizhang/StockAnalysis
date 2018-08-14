package com.zlzhang.stockmodel;

import java.util.List;
import java.util.Map;

/**
 * Created by zhangzhilai on 2018/8/7.
 */

public class ResultData {
    int code;
    Map<String, List<StockModel>> result;

    public Map<String, List<StockModel>> getResult() {
        return result;
    }

    public void setResult(Map<String, List<StockModel>> result) {
        this.result = result;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
