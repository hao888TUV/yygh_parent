/**
 * Created by hao
 * 2024/7/14 18:55
 */
package com.yygh.common.exception;

import com.yygh.common.result.Result;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public Result<Object> error(Exception e){
        e.printStackTrace();
        return Result.fail();
    }

    @ExceptionHandler(YyghException.class)
    public Result<Object> error(YyghException e){
        e.printStackTrace();
        String message = e.getMessage();
        Integer code = e.getCode();
        Result<Object> result = Result.build(code, message);
//        return Result.fail();
        return result;
    }


}
