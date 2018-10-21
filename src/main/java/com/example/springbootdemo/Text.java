package com.example.springbootdemo;


import com.alibaba.fastjson.JSON;

import java.util.List;

public class Text {
    public static void main(String[] args) {
        String json = "{" +
                "\"code\":1," +
                "\"message\":\"adnksn\"," +
                "\"data\":[{" +
                "\"name\":\"123\"" +
                "}," +
                "{" +
                "\"name\":\"123\"" +
                "}]" +
                "}";
        String json1 = "{" +
                "\"code\":1," +
                "\"message\":\"adnksn\"," +
                "\"data\":[]" +
                "}";
        String json2 = "{" +
                "\"code\":1," +
                "\"message\":\"adnksn\"," +
                "\"data\":[\"\",\"\"]" +
                "}";
        String json3 = "{" +
                "\"code\":1," +
                "\"message\":\"adnksn\"," +
                "\"data\":{" +
                "\"name\":\"123\"" +
                "}" +
                "}";
        String json4 = "{" +
                "\"code\":1," +
                "\"message\":\"adnksn\"," +
                "\"data\":\"\"" +
                "}";
//        JSONObject jsonObject = JSON.parseObject(json);
//        System.out.println("fastjson1-->:"+jsonObject);
//        BaseBean<BaseBean.DataBean> beanBean1 = new BaseBean<>();
        Bean1 bean2 = JSON.parseObject(json, Bean1.class);
        System.out.println("fastjson2-->:" + bean2);
        System.out.println("message-->:" + bean2.message);
        System.out.println("code-->:" + bean2.code);
        System.out.println("name-->:" + bean2.name);
        List<Bean1> data = bean2.data;
        System.out.println("data.name-->"+data.get(0).name);
//        Bean2 bean3 = JSON.parseObject(json3, Bean2.class);
//        System.out.println("fastjson3-->:"+bean3);
//        Bean2 bean4 = JSON.parseObject(json4, Bean2.class);
//        System.out.println("fastjson4-->:"+bean4);
//        BaseBean bean5 = JSON.parseObject(json2, BaseBean.class);
//        System.out.println("fastjson5-->:"+bean5);

//        Gson gson = new Gson();
//        BaseBean<String> bean5 = gson.fromJson(json, BaseBean.class);
//        System.out.println("gson-->:"+bean5);
        /*Gson gson = new Gson();
        BaseBean bean5 = gson.fromJson(json, BaseBean.class);
        System.out.println("gson-->:"+bean5);
        BaseBean bean6 = gson.fromJson(json1, BaseBean.class);
        System.out.println("gson-->:"+bean6);
        Bean2 bean7 = gson.fromJson(json3, Bean2.class);
        System.out.println("gson-->:"+bean7);
        Bean2 bean8 = gson.fromJson(json4, Bean2.class);
        System.out.println("gson-->:"+bean8);*/
    }
}
