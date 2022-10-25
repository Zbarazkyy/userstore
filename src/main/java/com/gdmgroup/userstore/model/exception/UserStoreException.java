package com.gdmgroup.userstore.model.exception;

import static com.gdmgroup.userstore.model.constant.LogStatic.NO_REF;
import static org.springframework.util.StringUtils.hasText;

import com.gdmgroup.userstore.model.enums.ErrorCode;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class UserStoreException extends RuntimeException {
    private final ErrorCode errorCode;
    private final String message;
    private final String requestRef;
    private final String processRef;
    private final String method;

    public UserStoreException(ErrorCode errorCode, String requestRef, String processRef, String message, String method) {
        super(hasText(message) ? message : errorCode.getMessage());
        this.errorCode = errorCode;
        this.requestRef = requestRef;
        this.processRef = hasText(processRef) ? processRef : NO_REF;
        this.message = message;
        this.method = method;
    }
}
