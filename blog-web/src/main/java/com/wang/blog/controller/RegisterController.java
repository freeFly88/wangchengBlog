package com.wang.blog.controller;

import com.wang.blogsystem.common.Result;
import com.wang.blogsystem.dto.UserDto;
import com.wang.blogsystem.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author cheng
 * @create 2022-10-18-17:16
 */
@RestController
public class RegisterController {

    @Autowired
    private LoginService loginService;

    @RequestMapping("/register")
    public Result register(@RequestBody UserDto userDto){
        return loginService.register(userDto);
    }

}
