package com.example.dream_shop_connectingsql.service.user;


import com.example.dream_shop_connectingsql.dto.UserDto;
import com.example.dream_shop_connectingsql.model.User;
import com.example.dream_shop_connectingsql.request.CreateUserRequest;
import com.example.dream_shop_connectingsql.request.UserUpdateRequest;

public interface IUserService {

    User getUserById(Long userId);
    User createUser(CreateUserRequest request);
    User updateUser(UserUpdateRequest request, Long userId);
    void deleteUser(Long userId);

    UserDto convertUserToDto(User user);
}
