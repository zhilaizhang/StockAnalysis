package com.zlzhang.stockanalysis.timer;

/**
 * Created by zhangzhilai on 2018/8/15.
 */

public class NFDFlightDataTaskListener  implements  ServletContextListener {

    public void contextInitialized(ServletContextEvent sce) {
        new TimerManager();
    }

    public void contextDestroyed(ServletContextEvent sce) {
        // TODO Auto-generated method stub

    }

}
