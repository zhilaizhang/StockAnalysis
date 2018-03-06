package com.zlzhang.stockanalysis;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.zlzhang.stockanalysis.list.view.SHActivity;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                //String url = "http://hq.sinajs.cn/list=sh601006";
//                String url = "http://hq.sinajs.cn/list=sh601006";
//                Log.d("test", "test" +  getHttpURLConnection(url, ""));
//            }
//        }).start();

//        readTest();
    }

    private void readTest(){
        final String sh = StockAnalysisUtil.readAssetsTxt(this, "sh");
        final String[] shStocks = sh.split(",");
        String sz = StockAnalysisUtil.readAssetsTxt(this, "sz");
        String[] szStocks = sz.split(",");
        int shStocksSize = shStocks.length;
        int szStocksSize = szStocks.length;

        final String[] allStocks = new String[shStocksSize + szStocksSize];
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (String shStock : shStocks) {
                    String url = "http://hq.sinajs.cn/list=sh" + shStock;
                   String result =  getHttpURLConnection(url, "");
                   StockAnalysisUtil.changeToModel(result);
                   System.out.println(result);

                }

            }
        }).start();

//        for (String szStock : szStocks) {
//            allStocks[i++] = szStock;
//        }

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


    public void gotoList(View view) {
        Intent intent = new Intent();
        intent.setClass(MainActivity.this, SHActivity.class);
        startActivity(intent);
    }
}
