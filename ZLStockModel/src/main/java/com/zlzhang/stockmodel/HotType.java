package com.zlzhang.stockmodel;

import java.io.Serializable;

/**
 * Created by zhangzhilai on 2018/3/4.
 * 热点类型，比如钢铁，贸易战等
 */

public class HotType implements Serializable{

    private int    id;              //数据库存储id
    private String code;           //股票代码
    private String name;           //热点类型

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
