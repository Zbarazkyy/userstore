package com.gdmgroup.userstore.controller;

import static com.gdmgroup.userstore.model.constant.HttpHeader.PROCESS_REF_HEADER;
import static com.gdmgroup.userstore.model.constant.LogStatic.REQUEST_LOG;
import static com.gdmgroup.userstore.model.constant.LogStatic.RESPONSE_LOG;
import static com.gdmgroup.userstore.utils.LogUtils.logError;

import com.gdmgroup.userstore.model.client.request.common.DataRequest;
import com.gdmgroup.userstore.model.client.request.userstore.SetUserRequest;
import com.gdmgroup.userstore.model.enums.ErrorCode;
import com.gdmgroup.userstore.model.exception.UserStoreException;
import com.gdmgroup.userstore.model.client.request.common.BaseRequest;
import com.gdmgroup.userstore.model.client.response.common.BaseResponse;
import com.gdmgroup.userstore.model.user.UserDto;
import com.gdmgroup.userstore.service.UserService;
import com.gdmgroup.userstore.service.common.converter.ResponseConverter;
import com.gdmgroup.userstore.utils.JsonUtils;
import java.util.List;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
@RequestMapping(value = "/api/v1/clients-store", produces = {MediaType.APPLICATION_JSON_VALUE})
public class ApiV1Controller {

    private final JsonUtils jsonUtils;
    private final UserService userService;
    private final ResponseConverter converter;

    private static final String METHOD_GET_USER_BY_ID = "getUserById";
    private static final String METHOD_GET_USER_BY_LEVEL_ID = "getUsersByLevelId";
    private static final String METHOD_GET_SET_USER = "setUser";

    public ApiV1Controller(JsonUtils jsonUtils, UserService userService, ResponseConverter converter) {
        this.jsonUtils = jsonUtils;
        this.userService = userService;
        this.converter = converter;
    }

    @GetMapping(value = "/userinfo/{userId}")
    public ResponseEntity<BaseResponse> getUserById(@PathVariable Long userId,
                                                    @RequestBody BaseRequest request,
                                                    @RequestHeader(name = PROCESS_REF_HEADER) String processRef) {

        request.setProcessRef(processRef);
        log.info(REQUEST_LOG, request.getRequestRef(), request.getProcessRef(), METHOD_GET_USER_BY_ID, jsonUtils.getObjectAsString(request));

        try {
            List<UserDto> userById = userService.getUserById(request, userId);

            log.info(RESPONSE_LOG, request.getRequestRef(), processRef, METHOD_GET_USER_BY_ID);
            return converter.generateResponse(userById, request.getRequestRef());

        } catch (UserStoreException e) {
            logError(request.getRequestRef(), processRef, e.getMethod(), e);
            throw e;
        } catch (Exception e) {
            logError(request.getRequestRef(), processRef, METHOD_GET_USER_BY_ID, e);
            throw new UserStoreException(ErrorCode.INTERNAL_SERVER_ERROR,
                    request.getRequestRef(), processRef, e.getMessage(), METHOD_GET_USER_BY_ID);
        }
    }

    @GetMapping(value = "/levelinfo/{levelId}")
    public ResponseEntity<BaseResponse> getUsersByLevelId(@PathVariable Long levelId,
                                                          @RequestBody BaseRequest request,
                                                          @RequestHeader(name = PROCESS_REF_HEADER) String processRef) {

        request.setProcessRef(processRef);
        log.info(REQUEST_LOG, request.getRequestRef(), request.getProcessRef(), METHOD_GET_USER_BY_LEVEL_ID, jsonUtils.getObjectAsString(request));

        try {
            List<UserDto> userById = userService.getUsersByLevelId(request, levelId);

            log.info(RESPONSE_LOG, request.getRequestRef(), processRef, METHOD_GET_USER_BY_LEVEL_ID);
            return converter.generateResponse(userById, request.getRequestRef());

        } catch (UserStoreException e) {
            logError(request.getRequestRef(), processRef, e.getMethod(), e);
            throw e;
        } catch (Exception e) {
            logError(request.getRequestRef(), processRef, METHOD_GET_USER_BY_LEVEL_ID, e);
            throw new UserStoreException(ErrorCode.INTERNAL_SERVER_ERROR,
                    request.getRequestRef(), processRef, e.getMessage(), METHOD_GET_USER_BY_LEVEL_ID);
        }
    }

    @PostMapping(value = "/setinfo")
    public ResponseEntity<BaseResponse> setUser(@RequestBody DataRequest<SetUserRequest> request,
                                                @RequestHeader(name = PROCESS_REF_HEADER) String processRef) {

        request.setProcessRef(processRef);
        log.info(REQUEST_LOG, request.getRequestRef(), request.getProcessRef(), METHOD_GET_SET_USER, jsonUtils.getObjectAsString(request));

        try {
            userService.setUser(request);

            log.info(RESPONSE_LOG, request.getRequestRef(), processRef, METHOD_GET_SET_USER);
            return converter.generateResponse(request.getRequestRef());

        } catch (UserStoreException e) {
            logError(request.getRequestRef(), processRef, e.getMethod(), e);
            throw e;
        } catch (Exception e) {
            logError(request.getRequestRef(), processRef, METHOD_GET_SET_USER, e);
            throw new UserStoreException(ErrorCode.INTERNAL_SERVER_ERROR,
                    request.getRequestRef(), processRef, e.getMessage(), METHOD_GET_SET_USER);
        }
    }
}

