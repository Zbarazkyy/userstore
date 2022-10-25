package com.gdmgroup.userstore.service.common.validation;

import com.gdmgroup.userstore.model.client.request.common.BaseRequest;
import java.util.List;
import lombok.NonNull;

public interface ValidationService {

    <T extends BaseRequest> void validateRequest(T request);

    <T> void validateObject(@NonNull T object, @NonNull String requestRef, String processRef);

    <T> List<String> validateFields(@NonNull T object);
}
