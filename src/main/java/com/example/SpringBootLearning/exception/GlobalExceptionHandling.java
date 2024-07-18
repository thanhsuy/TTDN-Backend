package com.example.SpringBootLearning.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.SpringBootLearning.request.ApiReponse;

@ControllerAdvice
public class GlobalExceptionHandling {
    @ExceptionHandler(value = AppException.class)
    public ResponseEntity<ApiReponse> handlingAppException(AppException exception) {
        ApiReponse apiReponse = new ApiReponse();
        apiReponse.setCode(exception.getErrorCode().getCode());
        apiReponse.setMessage(exception.getErrorCode().getMessage());
        return ResponseEntity.badRequest().body(apiReponse);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ApiReponse> handlingMethodArgumentNotValidException(
            MethodArgumentNotValidException exception) {
        String errorKey = exception.getFieldError().getDefaultMessage();
        ErrorCode errorCode = ErrorCode.valueOf(errorKey);
        ApiReponse apiReponse = new ApiReponse();
        apiReponse.setMessage(errorCode.getMessage());
        apiReponse.setCode(errorCode.getCode());
        return ResponseEntity.badRequest().body(apiReponse);
    }
}
