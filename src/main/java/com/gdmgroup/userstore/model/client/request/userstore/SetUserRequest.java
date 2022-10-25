package com.gdmgroup.userstore.model.client.request.userstore;

import static com.gdmgroup.userstore.model.constant.ValidatorErrorMessage.NOT_NULL;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class SetUserRequest {

    @JsonProperty("user_id")
    @NotNull(message = NOT_NULL)
    private Long userId;

    @JsonProperty("level_id")
    @NotNull(message = NOT_NULL)
    private Long levelId;

    @JsonProperty("result")
    @NotNull(message = NOT_NULL)
    private Long result;
}
