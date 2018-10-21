package com.example.springbootdemo.resultBean.common;

public class ResultBean<B> {
    private int code;
    private B data;
    private String message;

    public static ResultBean success() {
        return ResultBean.success((BaseBean) null);
    }

    public static ResultBean success(String message) {
        return ResultBean.success(null, message);
    }

    public static <BEAN> ResultBean<BEAN> success(BEAN data) {
        return ResultBean.success(data, "操作成功");
    }

    public static <BEAN > ResultBean<BEAN> success(BEAN data, String message) {
        ResultBean<BEAN> resultBean = new ResultBean<>();
        resultBean.code = ResultCode.SUCCESS;
        resultBean.data = data;
        resultBean.message = message;
        return resultBean;
    }

    public static <BEAN > ResultBean<BEAN> fail() {
        return ResultBean.fail(null, "操作失败");
    }

    public static <BEAN > ResultBean<BEAN> fail(BEAN data) {
        return ResultBean.fail(data, "操作失败");
    }

    public static  ResultBean fail(String message) {
        return ResultBean.fail( null, message);
    }

    public static <BEAN > ResultBean<BEAN> fail(BEAN data, String message) {
        ResultBean resultBean = new ResultBean<>();
        resultBean.code = ResultCode.FAIL;
        resultBean.data = data;
        resultBean.message = message;
        return resultBean;
    }

    public static ResultBean exception() {
        return ResultBean.exception(null, "操作异常");
    }

    public static <BEAN > ResultBean<BEAN> exception(BEAN data) {
        return ResultBean.exception(data, "操作异常");
    }

    public static <BEAN> ResultBean<BEAN> exception(BEAN data, String message) {
        ResultBean<BEAN> resultBean = new ResultBean<>();
        resultBean.code = ResultCode.EXCEPTION;
        resultBean.data = data;
        resultBean.message = message;
        return resultBean;
    }
}
