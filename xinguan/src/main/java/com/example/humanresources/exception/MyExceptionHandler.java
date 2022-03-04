package com.example.humanresources.exception;

import com.example.humanresources.utils.R;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@ResponseBody
public class MyExceptionHandler {


    @ExceptionHandler(ServiceException.class)
    public R handlerServiceException(ServiceException e) {
        e.printStackTrace();
        return R.exp(e.getErrorCode(), e.getErrorMsg());
    }

    /**
     * 统一异常处理
     */
    @ExceptionHandler(Exception.class)
    public R handlerException(Exception e) {
        e.printStackTrace();
        return R.exp();
    }
}
