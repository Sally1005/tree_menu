package com.loo.tree.treemenu.common.exception;

import com.loo.tree.treemenu.common.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理类
 * @author
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {


    /**
     * 处理 Exception 异常
     *
     * @param e 异常
     * @return 处理结果
     */
    @ExceptionHandler(Exception.class)
    public Result handlerException(Exception e) {
        log.error(e.getMessage(), e);
        return Result.error().message("系统异常");
    }

    /**
     * 处理空指针异常
     *
     * @param e 异常
     * @return 处理结果
     */
    @ExceptionHandler(NullPointerException.class)
    public Result handlerNullPointerException(NullPointerException e) {
        log.error(e.getMessage(), e);
        return Result.error().message("空指针异常");
    }

    /**
     * 处理自定义异常
     *
     * @param e 异常
     * @return 处理结果
     */
    @ExceptionHandler(GlobalException.class)
    public Result handlerGlobalException(GlobalException e) {
        log.error(e.getMessage(), e);
        return Result.error().message(e.getMessage()).code(e.getCode());
    }
}
