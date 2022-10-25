package com.gdmgroup.userstore.repository.userstore;

import com.gdmgroup.userstore.model.user.UserDto;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import org.springframework.stereotype.Service;

@Service
public class UserStoreRepository {
    private static final ConcurrentMap<Long, List<UserDto>> userStore = new ConcurrentHashMap<>();

    public static List<UserDto> getUserById(Long userId) {
        return userStore.get(userId);
    }

    public static synchronized void setUser(UserDto user) {
        if (!userStore.containsKey(user.getUserId())) {
            userStore.put(user.getUserId(), new ArrayList(Arrays.asList(user)));
            return;
        }
        List<UserDto> usersList = userStore.get(user.getUserId());
        usersList.add(user);
        userStore.put(user.getUserId(), usersList);
    }

    public static List<UserDto> getUserByLevelId(Long levelId) {
        List<UserDto> usersList = new ArrayList<>();
        userStore.entrySet().stream().parallel().forEach(map -> {
            List<UserDto> value = map.getValue();
            value.stream().parallel().forEach(user -> {
                if (levelId.equals(user.getLevelId())) {
                    usersList.add(user);
                }
            });
        });
        return usersList;
    }

    public static Boolean isContains(UserDto user) {
        List<UserDto> usersList = userStore.get(user.getUserId());
        return usersList.contains(user);
    }
}
