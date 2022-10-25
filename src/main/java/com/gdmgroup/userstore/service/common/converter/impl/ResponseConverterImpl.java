package com.gdmgroup.userstore.service.common.converter.impl;

import com.gdmgroup.userstore.model.client.response.common.BaseErrorResponse;
import com.gdmgroup.userstore.model.client.response.common.BaseResponse;
import com.gdmgroup.userstore.model.enums.ErrorCode;
import com.gdmgroup.userstore.model.enums.ResponseResultCode;
import com.gdmgroup.userstore.service.common.converter.ResponseConverter;
import com.gdmgroup.userstore.utils.DateUtils;
import java.time.LocalDateTime;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.util.Strings;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class ResponseConverterImpl implements ResponseConverter {

    @Override
    public ResponseEntity<BaseResponse> generateResponse(final String requestRef) {
        return generateResponse(null, requestRef);
    }

    @Override
    public ResponseEntity<BaseResponse> generateResponse(Object data, String requestRef) {
        return ResponseEntity.ok(
                BaseResponse.builder()
                        .requestRef(requestRef)
                        .result(ResponseResultCode.OK)
                        .timestamp(LocalDateTime.now().format(DateUtils.getDateTimeFormatter()))
                        .data(data)
                        .build()
        );
    }

    @Override
    public ResponseEntity<BaseResponse> generateResponseByErrorCode(ErrorCode error, String message, String requestRef) {
        return getResponseBaseError(error, message, requestRef);
    }

    private ResponseEntity<BaseResponse> getResponseBaseError(ErrorCode error, String message, String requestRef) {
        return ResponseEntity
                .status(error.getHttpStatus())
                .body(BaseResponse.builder()
                        .requestRef(requestRef)
                        .result(ResponseResultCode.ERROR)
                        .timestamp(LocalDateTime.now().format(DateUtils.getDateTimeFormatter()))
                        .error(BaseErrorResponse.builder()
                                .status(error.getCode())
                                .title(Strings.isBlank(message) ? error.getMessage() : message)
                                .build())
                        .build()
                );
    }
}
