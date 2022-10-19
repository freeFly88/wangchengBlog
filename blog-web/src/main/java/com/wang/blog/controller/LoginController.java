package com.wang.blog.controller;

import com.wang.blogsystem.common.Result;
import com.wang.blogsystem.common.ResultCode;

import com.wang.blogsystem.dto.LoginDto;
import com.wang.blogsystem.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author cheng
 * @create 2022-10-13-16:07
 */
@RestController
@RequestMapping("/api")
public class LoginController {


    @Autowired
    private LoginService loginService;

    /**
     * 登录接口
     * @param loginDto
     * @return
     */
    @PostMapping("/login")
    public Result login(@RequestBody LoginDto loginDto) {
        return loginService.login(loginDto);
    }


}
