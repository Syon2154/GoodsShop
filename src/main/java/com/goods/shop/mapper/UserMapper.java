package com.goods.shop.mapper;

import com.goods.shop.dto.request.UserRequestDto;
import com.goods.shop.dto.response.UserResponseDto;
import com.goods.shop.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserResponseDto mapToDto(User user) {
        UserResponseDto responseDto = new UserResponseDto();
        responseDto.setId(user.getId());
        responseDto.setEmail(user.getEmail());
        responseDto.setPassword(user.getPassword());
        responseDto.setRoles(user.getRoles());
        return responseDto;
    }

    public User mapToModel(UserRequestDto requestDto) {
        User user = new User();
        user.setEmail(requestDto.getEmail());
        user.setRoles(requestDto.getRoles());
        return user;
    }
}
