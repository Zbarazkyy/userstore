package com.gdmgroup.userstore.model.client.request.common;

import static com.gdmgroup.userstore.model.constant.ValidatorErrorMessage.NOT_NULL;
import static com.gdmgroup.userstore.model.constant.ValidatorErrorMessage.NOT_BLANC;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = true)
public class DataRequest<T> extends BaseRequest {
    @JsonProperty("data")
    @NotNull(message = NOT_NULL)
    @Valid
    T data;

    @Builder
    public DataRequest(@Valid @NotNull(message = NOT_NULL) @NotBlank(message = NOT_BLANC) final String requestRef, final T data) {
        super(requestRef);
        this.data = data;
    }
}
