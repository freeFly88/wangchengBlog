package com.wang.blogsystem.service;

import com.wang.blogsystem.common.Result;
import com.wang.blogsystem.domain.User;
import com.wang.blogsystem.dto.LoginDto;
import com.wang.blogsystem.dto.UserDto;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.util.List;

/**
 * @author cheng
 * @create 2022-10-13-16:15
 */
public interface LoginService {
    List<User> findList(User user);

    Result login(LoginDto loginDto);

    Result register(UserDto userDto);

    Result getCode(HttpServletRequest request, HttpServletResponse response) throws IOException, FontFormatException;

}
