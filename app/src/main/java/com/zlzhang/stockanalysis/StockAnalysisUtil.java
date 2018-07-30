package com.zlzhang.stockanalysis;

import android.content.Context;

import com.zlzhang.stockanalysis.modle.StockModel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by zhangzhilai on 2018/3/2.
 */

public class StockAnalysisUtil {

    public static final String STOCK_TYPE = "stock_type";

    public static String readAssetsTxt(Context context, String fileName){
        try {
            //Return an AssetManager instance for your application's package
            InputStream is = context.getAssets().open(fileName+".txt");
            int size = is.available();
            // Read the entire asset into a local byte buffer.
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            // Convert the buffer into a string.
            String text = new String(buffer, "utf-8");
            // Finally stick the string into the text view.
            return text;
        } catch (IOException e) {
            // Should never happen!
//            throw new RuntimeException(e);
            e.printStackTrace();
        }
        return "读取错误，请检查文件名";
    }


    public static StockModel changeToModel(String rawStock){
        String stockCode = rawStock.substring(13, 19);
        String stockInfo = rawStock.substring(21);
        String[] strings  = stockInfo.split(",");
        if (strings.length < 30) {
            return null;
        }
        StockModel stockModel = new StockModel();
        stockModel.setCode(stockCode);
        stockModel.setName(strings[0]);
        stockModel.setTodayOpen(Float.parseFloat(strings[1]));
        stockModel.setYesterdayClose(Float.parseFloat(strings[2]));
        stockModel.setNowPrice(Float.parseFloat(strings[3]));
        stockModel.setTodayHighest(Float.parseFloat(strings[4]));
        stockModel.setTodayLowest(Float.parseFloat(strings[5]));
        stockModel.setDealNum(Long.parseLong(strings[8]));
        stockModel.setOBV(Double.parseDouble(strings[9]));
        stockModel.setDate(strings[30]);
        stockModel.setTime(strings[31]);

        return stockModel;
    }


    public static String getHttpURLConnection(String urlString,String params){

        String result = null;

        try {

            URL url = new URL(urlString+"?"+params);

            HttpURLConnection connection=(HttpURLConnection) url.openConnection();

            connection.setDoInput(true);//设置可输入

            connection.setDoOutput(true);//设置可以输出


            connection.setRequestProperty("contentType", "GBK");

            connection.setRequestMethod("GET");//设置请求方式//必须是大写

            connection.setConnectTimeout(5000);//设置连接超时时间。

            connection.setReadTimeout(5000);//设置读数据的超时时间

            // Post 请求不能使用缓存

            connection.setUseCaches(true);

            connection.connect();//连接服务器
            BufferedReader reader=new BufferedReader(new InputStreamReader(connection.getInputStream(), "GBK"));

            String data;

            StringBuilder builder=new StringBuilder();

            while((data=reader.readLine())!=null){

                builder.append(data);

            }

            result=builder.toString();

        }catch(Exception e) {

            e.printStackTrace();

        }

        return result;

    }


    public static  String postQuery(String urlPath, String json){

        String result = "";
        BufferedReader reader = null;
        try {
            URL url = new URL(urlPath);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setConnectTimeout(5 * 1000);
            conn.setReadTimeout(5 * 1000);
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            conn.setRequestProperty("Connection", "Keep-Alive");
            conn.setRequestProperty("Charset", "UTF-8");
            // 设置文件类型:
            conn.setRequestProperty("Content-Type","application/json; charset=UTF-8");
            // 设置接收类型否则返回415错误
            //conn.setRequestProperty("accept","*/*")此处为暴力方法设置接受所有类型，以此来防范返回415;
            conn.setRequestProperty("accept","application/json");

            if (json != null ) {
                byte[] writebytes = json.getBytes();
                // 设置文件长度
                conn.setRequestProperty("Content-Length", String.valueOf(writebytes.length));
                OutputStream outwritestream = conn.getOutputStream();
                outwritestream.write(writebytes);
                outwritestream.flush();
                outwritestream.close();
//                Log.d("hlhupload", "doJsonPost: conn"+conn.getResponseCode());
            }

            if (conn.getResponseCode() == 200) {
                reader = new BufferedReader(
                        new InputStreamReader(conn.getInputStream()));
                result = reader.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }


    /**
     * 计算股票的涨跌幅
     * @param nowPrice
     * @param yesterdayPrice
     * 涨跌幅度=(现价-昨收价)/昨收价*100% (计算值正为涨,负为跌)
     * @return
     */
    public static double getStockRiseRate(double nowPrice, double yesterdayPrice){
        double riseRate =  (yesterdayPrice - nowPrice) / yesterdayPrice;
        return riseRate;
    }

}
