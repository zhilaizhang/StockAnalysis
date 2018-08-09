package com.zlzhang.stockanalysis.modle;

import android.os.Environment;

/**
 * Created by zhangzhilai on 2018/8/7.
 */

public class GlobalVariable {

    public static String sServerIp = "";

    public static String BASE_PATH = Environment.getExternalStorageDirectory().getAbsolutePath();

    public static String STOCK_PATH = BASE_PATH + "/" + "stock/";

}
