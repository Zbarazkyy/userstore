package com.gdmgroup.userstore.service;

import com.gdmgroup.userstore.model.client.request.common.BaseRequest;
import com.gdmgroup.userstore.model.client.request.common.DataRequest;
import com.gdmgroup.userstore.model.client.request.userstore.SetUserRequest;
import com.gdmgroup.userstore.model.user.UserDto;
import java.util.List;

public interface UserService {
    List<UserDto> getUserById(BaseRequest request, Long userId);

    List<UserDto> getUsersByLevelId(BaseRequest request, Long levelId);

    void setUser(DataRequest<SetUserRequest> request);
}
