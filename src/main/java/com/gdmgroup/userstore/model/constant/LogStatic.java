package com.gdmgroup.userstore.model.constant;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class LogStatic {
    public static final String REQUEST_LOG = "{} {} REQUEST {}: {}";
    public static final String RESPONSE_LOG = "{} {} RESPONSE: {}";
    public static final String ERROR_LOG = "{} {} ERROR {}: {}";
    public static final String ERROR_INFO_LOG = "{} {} ERROR {}: {} With param:{}";
    public static final String INFO_LOG = "{}, {}";
    public static final String NO_REF = "no-ref";
}
