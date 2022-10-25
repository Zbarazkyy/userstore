package com.gdmgroup.userstore.model.client.response.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gdmgroup.userstore.model.enums.ResponseResultCode;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class BaseResponse {
    @JsonProperty("request_ref")
    String requestRef;

    @JsonProperty("response_ref")
    String responseRef;

    @JsonProperty("result")
    ResponseResultCode result;

    @JsonProperty("timestamp")
    String timestamp;

    @JsonProperty("data")
    Object data;

    @JsonProperty("error")
    BaseErrorResponse error;
}
