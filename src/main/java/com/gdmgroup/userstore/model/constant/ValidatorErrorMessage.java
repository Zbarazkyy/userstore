package com.gdmgroup.userstore.model.constant;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ValidatorErrorMessage {
    public static final String NOT_NULL = "The parameter cannot be null";
    public static final String NOT_BLANC = "The parameter cannot be blanc";
}
