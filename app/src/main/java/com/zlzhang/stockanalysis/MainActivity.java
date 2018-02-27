package com.zlzhang.stockanalysis;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Thread(new Runnable() {
            @Override
            public void run() {
                String url = "http://hq.sinajs.cn/list=sh601006";
                Log.d("test", "test" +  getHttpURLConnection(url, ""));
            }
        }).start();

    }

    public static String getHttpURLConnection(String urlString,String params){

        String result = null;

        try {

            URL url = new URL(urlString+"?"+params);

            HttpURLConnection connection=(HttpURLConnection) url.openConnection();

            connection.setDoInput(true);//设置可输入

            connection.setDoOutput(true);//设置可以输出

            // (如果不设此项,在传送序列化对象时,当WEB服务默认的不是这种类型时可能抛java.io.EOFException)

            connection.setRequestProperty("Content-type","application/x-java-serialized-object");

            connection.setRequestMethod("GET");//设置请求方式//必须是大写

            connection.setConnectTimeout(5000);//设置连接超时时间。

            connection.setReadTimeout(5000);//设置读数据的超时时间

            // Post 请求不能使用缓存

            connection.setUseCaches(true);

            connection.connect();//连接服务器

            BufferedReader reader=new BufferedReader(new InputStreamReader(connection.getInputStream()));

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


}
