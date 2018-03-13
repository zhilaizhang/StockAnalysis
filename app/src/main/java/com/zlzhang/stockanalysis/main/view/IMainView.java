package com.zlzhang.stockanalysis.main.view;

/**
 * Created by zhangzhilai on 2018/3/13.
 */

public interface IMainView {

    void showLoadingView(boolean isShow);

    void dataGot(boolean isGot);

    void showCount(int count);

}
