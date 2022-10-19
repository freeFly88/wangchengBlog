package com.wang.blogsystem.service;

import com.wang.blogsystem.common.Result;
import com.wang.blogsystem.domain.User;
import com.wang.blogsystem.dto.LoginDto;
import com.wang.blogsystem.dto.UserDto;

import java.util.List;

/**
 * @author cheng
 * @create 2022-10-13-16:15
 */
public interface LoginService {
    List<User> findList(User user);

    Result login(LoginDto loginDto);

    Result register(UserDto userDto);
}
