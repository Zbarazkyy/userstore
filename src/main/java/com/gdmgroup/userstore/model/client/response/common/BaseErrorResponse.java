package com.gdmgroup.userstore.model.client.response.common;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class BaseErrorResponse {
    String status;
    String title;
}
