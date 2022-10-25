package com.gdmgroup.userstore.service.common.validation.impl;

import com.gdmgroup.userstore.model.enums.ErrorCode;
import com.gdmgroup.userstore.model.exception.UserStoreException;
import com.gdmgroup.userstore.model.client.request.common.BaseRequest;
import com.gdmgroup.userstore.service.common.validation.ValidationService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import lombok.NonNull;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class ValidationServiceImpl implements ValidationService {
    private Validator beanFieldsValidator;

    @PostConstruct
    private void init() {
        beanFieldsValidator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Override
    public <T extends BaseRequest> void validateRequest(@NonNull final T request) {
        validateObject(request, request.getRequestRef(), request.getProcessRef());
    }

    @Override
    public <T> void validateObject(@NonNull final T object, @NonNull final String requestRef, final String processRef) {
        List<String> errorList = validateFields(object);
        if (!errorList.isEmpty()) {
            doValidateException(requestRef, processRef, errorList.toString());
        }
    }

    @Override
    public <T> List<String> validateFields(@NonNull final T object) {
        List<String> errorList = new ArrayList<>();
        Set<ConstraintViolation<T>> violations = beanFieldsValidator.validate(object);

        if (!violations.isEmpty()) {
            Map<String, String> errorMap = new HashMap<>();
            for (ConstraintViolation<T> violation : violations) {
                errorMap.put(violation.getPropertyPath().toString(), violation.getMessage());
            }
            errorList.add(errorMap.toString());
        }

        return errorList;
    }

    private void doValidateException(final String requestRef, final String processRef, final String message) {
        throw new UserStoreException(ErrorCode.VALIDATE_ERROR, requestRef, processRef, message, "doValidateException");
    }
}
