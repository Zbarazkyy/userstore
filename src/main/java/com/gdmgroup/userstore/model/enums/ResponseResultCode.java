package com.gdmgroup.userstore.model.enums;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ResponseResultCode {

    @JsonProperty("ok")
    OK("ok"),

    @JsonProperty("error")
    ERROR("error"),

    @JsonProperty("in_process")
    IN_PROCESS("in_process");

    private final String code;
}
