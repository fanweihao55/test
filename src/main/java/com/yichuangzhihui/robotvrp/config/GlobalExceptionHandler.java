package com.yichuangzhihui.robotvrp.config;

import com.yichuangzhihui.robotvrp.util.Result;
import com.yichuangzhihui.robotvrp.util.ServiceException;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理
 */
@RestControllerAdvice
@Log4j2
public class GlobalExceptionHandler {

    /**
     * 业务异常处理
     * @param e
     * @return
     */
    @ExceptionHandler(ServiceException.class)
    public Result emptyResultExceptionHandler(ServiceException e){
        log.error("查询结果为空：{}",e.getMessage());
        Result result = new Result();
        result.setErrorMsg(e.getCode().toString(),e.getMessage());
        return result;
    }


}
