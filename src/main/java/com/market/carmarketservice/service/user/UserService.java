package com.market.carmarketservice.service.user;

import com.market.carmarketservice.request.auth.AuthenticationRequest;
import com.market.carmarketservice.model.user.User;
import com.market.carmarketservice.request.user.UserRequest;
import com.market.carmarketservice.response.user.UserResponse;

import java.util.List;

public interface UserService {
    List<UserResponse> getUsers();

    UserResponse getUser(int id);

    boolean updateUser(User user, int id);

    boolean updateRole(User user, int id);

    boolean deleteUser(int id);

    boolean isUser(int id);

    boolean existUser(String username);

    boolean validPassword(AuthenticationRequest request);

    boolean changeAvatar(UserRequest avatar, int id);
}
