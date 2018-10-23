package com.example.springbootdemo.error;

import com.example.springbootdemo.resultBean.common.ResultBean;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MultipartException;

@ControllerAdvice
public class ExceptionProcess {
    // 对这个异常的统一处理，返回值 和Controller的返回规则一样
    @ExceptionHandler(MultipartException.class)
    public ResultBean handleAll(Throwable t){
        t.printStackTrace();
        // TODO do sth
        return ResultBean.exception();
    }
}
