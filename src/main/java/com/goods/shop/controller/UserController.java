package com.goods.shop.controller;

import com.goods.shop.dto.request.UserRequestDto;
import com.goods.shop.dto.response.UserResponseDto;
import com.goods.shop.mapper.UserMapper;
import com.goods.shop.model.User;
import com.goods.shop.service.UserService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @PostMapping
    public UserResponseDto add(UserRequestDto userRequestDto) {
        User user = userMapper.mapToModel(userRequestDto);
        return userMapper.mapToDto(userService.add(user));
    }

    @GetMapping("/by-email")
    public UserResponseDto findByEmail(@RequestParam String email) {
        return userMapper.mapToDto(userService.findByEmail(email));
    }

    @GetMapping
    public List<UserResponseDto> findAll() {
        return userService.findAll().stream()
                .map(userMapper::mapToDto)
                .collect(Collectors.toList());
    }
}
