package com.gdmgroup.userstore.service.impl;

import static com.gdmgroup.userstore.model.constant.LogStatic.ERROR_INFO_LOG;

import com.gdmgroup.userstore.model.client.request.common.BaseRequest;
import com.gdmgroup.userstore.model.client.request.common.DataRequest;
import com.gdmgroup.userstore.model.client.request.userstore.SetUserRequest;
import com.gdmgroup.userstore.model.enums.ErrorCode;
import com.gdmgroup.userstore.model.exception.UserStoreException;
import com.gdmgroup.userstore.model.user.UserDto;
import com.gdmgroup.userstore.repository.userstore.UserStoreRepository;
import com.gdmgroup.userstore.service.UserService;
import com.gdmgroup.userstore.service.common.validation.ValidationService;
import com.gdmgroup.userstore.utils.JsonUtils;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class UserServiceImpl implements UserService {
    private final ValidationService validation;
    private final JsonUtils jsonUtils;

    private static final String METHOD_SET_USER = "UserServiceImpl.setUser";
    private static final String METHOD_GET_USER_BY_ID = "UserServiceImpl.getUserById";

    public UserServiceImpl(ValidationService validation, JsonUtils jsonUtils) {
        this.validation = validation;
        this.jsonUtils = jsonUtils;
    }

    @Override
    public List<UserDto> getUserById(BaseRequest request, Long userId) {
        validation.validateRequest(request);
        validation.validateObject(userId, request.getRequestRef(), request.getProcessRef());
        List<UserDto> userById = UserStoreRepository.getUserById(userId);
        if (userById.isEmpty()) {
            throw new UserStoreException(ErrorCode.BAD_REQUEST, request.getRequestRef(), request.getProcessRef(),
                    String.format("User with id: %s is not in the database", userById), METHOD_GET_USER_BY_ID);
        }
        return userById.stream()
                .parallel()
                .sorted(Comparator.comparing(UserDto::getResult)
                        .thenComparing(UserDto::getLevelId)
                        .reversed())
                .limit(20)
                .collect(Collectors.toList());
    }

    @Override
    public List<UserDto> getUsersByLevelId(BaseRequest request, Long levelId) {
        validation.validateRequest(request);
        validation.validateObject(levelId, request.getRequestRef(), request.getProcessRef());
        List<UserDto> userByLevelId = UserStoreRepository.getUserByLevelId(levelId);
        return userByLevelId.stream()
                .parallel()
                .sorted(Comparator.comparing(UserDto::getResult)
                        .thenComparing(UserDto::getUserId).reversed())
                .limit(20)
                .collect(Collectors.toList());
    }

    @Override
    public void setUser(DataRequest<SetUserRequest> request) {
        validation.validateRequest(request);
        SetUserRequest data = request.getData();
        UserDto user = UserDto.builder()
                .userId(data.getUserId())
                .levelId(data.getLevelId())
                .result(data.getResult())
                .build();
        UserStoreRepository.setUser(user);

        if (Boolean.FALSE.equals(UserStoreRepository.isContains(user))) {
            log.error(ERROR_INFO_LOG, request.getRequestRef(), request.getProcessRef(), METHOD_SET_USER, "The user has not been added", jsonUtils.getObjectAsString(user));
            throw new UserStoreException(ErrorCode.CLIENT_DOES_NOT_EXIST,
                    request.getRequestRef(), request.getProcessRef(), "The user has not been added", METHOD_SET_USER);
        }
    }
}
