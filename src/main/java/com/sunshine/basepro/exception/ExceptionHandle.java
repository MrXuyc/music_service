package com.sunshine.basepro.exception;

import com.sunshine.basepro.util.Result;
import com.sunshine.basepro.util.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 需要添加@ControllerAdvice
 * 在方法上添加
 * @ExceptionHandler(value=Exception.class)
  *  返回是string 或者是 json   @ResponseBody
 *  返回是视图 则返回ModelAndView
 */
@ControllerAdvice
public class ExceptionHandle {

    private final static Logger logger = LoggerFactory.getLogger(ExceptionHandle.class);

    @ExceptionHandler(value=Exception.class)
    @ResponseBody
    public Result handle(Exception e){
        if(e instanceof  CustomException){
            return  ResultUtil.error(((CustomException)e).getCode(),e.getMessage());
        }
        logger.info("系统异常 {}",e);
        return ResultUtil.error("-1","未知错误");
    }

}
