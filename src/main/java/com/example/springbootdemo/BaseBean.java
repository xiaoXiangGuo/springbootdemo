package com.example.springbootdemo;

import java.util.List;

/**
 * author:  zhouchaoxiang
 * date:    2018/10/17
 * explain: 
 */
public    class BaseBean<bean>   {

    /**
     * code : 1
     * message : adnksn
     * data : [{"name":"123"},{"name":"123"}]
     */

    public int code;
    public String message;
    public List<bean> data;

    @Override
    public String toString() {
        return "BaseBean{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
