package com.example.springbootdemo;

public class Bean1 extends BaseBean<Bean1> {
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
