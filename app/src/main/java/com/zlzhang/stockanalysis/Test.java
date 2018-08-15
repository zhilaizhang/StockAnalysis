package com.zlzhang.stockanalysis;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by zhangzhilai on 2018/8/15.
 */

public class Test {
    /*
     * 将当前日期加减n天数。 如传入整型-5 意为将当前日期减去5天的日期 如传入整型5 意为将当前日期加上5天后的日期 返回字串 例(19990203)
     */
    public static Date dateAdd(int days) {
        // 日期处理模块 (将日期加上某些天或减去天数)返回字符串
        Calendar calendar = Calendar.getInstance(); // java.util包
//        calendar.add(Calendar.DATE, days); // 日期减 如果不够减会将月变动
        int i = 0;
        while(i < days){
            calendar.add(Calendar.DATE, -1);
            i++;
            if(calendar.get(Calendar.DAY_OF_WEEK)==Calendar.SATURDAY ||
                    calendar.get(Calendar.DAY_OF_WEEK)==Calendar.SUNDAY){
                i--;
            }
        }
        return calendar.getTime();
    }
    public static void main(String[] args) {
        Date date = Test.dateAdd(4);
        SimpleDateFormat sdfd = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println("50天前日期为：" + sdfd.format(date));
        SimpleDateFormat sdf = new SimpleDateFormat("E");
        System.out.println("为：" + sdf.format(date));
    }
}
