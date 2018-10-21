package com.example.springbootdemo;

import java.util.List;

/**
 * author:  zhouchaoxiang
 * date:    2018/10/17
 * explain: 
 */
public    class Bean2 {

    /**
     * code : 1
     * message : adnksn
     * data : [{"name":"123"},{"name":"123"}]
     */

    public int code;
    public String message;
    public DataBean data;

    public static class DataBean {
        /**
         * name : 123
         */

        public String name;

        @Override
        public String toString() {
            return "DataBean{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "Bean2{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
