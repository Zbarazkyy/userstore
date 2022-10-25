package com.gdmgroup.userstore.utils;

import static com.gdmgroup.userstore.model.constant.LogStatic.ERROR_LOG;
import static com.gdmgroup.userstore.model.constant.LogStatic.NO_REF;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class LogUtils {
    public static void logError(final String requestRef, final String processRef, final String method, Exception e) {
        log.error(ERROR_LOG, requestRef, processRef, method, e.getMessage(), e);
    }
}
