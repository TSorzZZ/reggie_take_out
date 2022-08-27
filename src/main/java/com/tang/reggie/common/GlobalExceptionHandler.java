package com.tang.reggie.common;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLIntegrityConstraintViolationException;

/**
 * 全局异常处理器  统一对Controller中的异常进行处理
 */
@ControllerAdvice(annotations = {RestController.class, Controller.class})   //给Controller控制器添加统一的操作或处理。
@ResponseBody  //java对象转为json格式的数据
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public R<String> exceptionHandler (SQLIntegrityConstraintViolationException exception){
        log.error(exception.getMessage());
        if (exception.getMessage().contains("Duplicate entry")){
            String[] split = exception.getMessage().split(" ");
            String msg = split[2] + "已存在";
            return R.error(msg);
        }
        return R.error("unknown error");
    }

    @ExceptionHandler(CustomException.class)
    public R<String> exceptionHandler (CustomException exception){
        log.error(exception.getMessage());

        return R.error(exception.getMessage());
    }

}