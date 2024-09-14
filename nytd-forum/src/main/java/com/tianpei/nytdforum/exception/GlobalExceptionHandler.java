package com.tianpei.nytdforum.exception;

import com.tianpei.nytdforum.pojo.Result;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Result> handleException(Exception e) {
        e.printStackTrace();
        Result result = Result.error(StringUtils.hasLength(e.getMessage()) ? e.getMessage() : "操作失败");
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR) // 或其他适当的状态码
                .body(result);
    }
}
