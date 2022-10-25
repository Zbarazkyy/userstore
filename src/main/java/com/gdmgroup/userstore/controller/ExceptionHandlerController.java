package com.gdmgroup.userstore.controller;

import static com.gdmgroup.userstore.model.constant.LogStatic.ERROR_LOG;
import static com.gdmgroup.userstore.model.constant.LogStatic.NO_REF;

import com.gdmgroup.userstore.model.client.response.common.BaseResponse;
import com.gdmgroup.userstore.model.enums.ErrorCode;
import com.gdmgroup.userstore.model.exception.UserStoreException;
import com.gdmgroup.userstore.service.common.converter.ResponseConverter;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Log4j2
@RestControllerAdvice
public class ExceptionHandlerController {

    private final ResponseConverter responseConverter;

    public ExceptionHandlerController(ResponseConverter responseConverter) {
        this.responseConverter = responseConverter;
    }

    @ExceptionHandler(value = UserStoreException.class)
    public ResponseEntity<BaseResponse> handleA24Exception(UserStoreException ex) {
        return responseConverter.generateResponseByErrorCode(ex.getErrorCode(), ex.getMessage(), ex.getRequestRef());
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<BaseResponse> handleAll(Exception ex) {
        log.error(ERROR_LOG, NO_REF, NO_REF, "handleAll", ex.getMessage(), ex);
        return responseConverter.generateResponseByErrorCode(ErrorCode.INTERNAL_SERVER_ERROR, ex.getMessage(), NO_REF);
    }
}
