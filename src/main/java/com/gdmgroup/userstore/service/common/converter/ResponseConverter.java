package com.gdmgroup.userstore.service.common.converter;

import com.gdmgroup.userstore.model.client.response.common.BaseResponse;
import com.gdmgroup.userstore.model.enums.ErrorCode;
import org.springframework.http.ResponseEntity;

public interface ResponseConverter {
    ResponseEntity<BaseResponse> generateResponse(Object data, String requestRef);

    ResponseEntity<BaseResponse> generateResponse(String requestRef);

    ResponseEntity<BaseResponse> generateResponseByErrorCode(ErrorCode error, String message, String requestRef);
}
