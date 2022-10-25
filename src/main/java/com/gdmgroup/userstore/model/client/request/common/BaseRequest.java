package com.gdmgroup.userstore.model.client.request.common;

import static com.gdmgroup.userstore.model.constant.ValidatorErrorMessage.NOT_NULL;
import static com.gdmgroup.userstore.model.constant.ValidatorErrorMessage.NOT_BLANC;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BaseRequest {
    @Valid
    @NotNull(message = NOT_NULL)
    @NotBlank(message = NOT_BLANC)
    @JsonProperty("request_ref")
    protected String requestRef;

    @JsonProperty("process_ref")
    protected String processRef;

    public BaseRequest(final String requestRef) {
        this.requestRef = requestRef;
    }

    public BaseRequest(String requestRef, String processRef) {
        this.requestRef = requestRef;
        this.processRef = processRef;
    }
}
