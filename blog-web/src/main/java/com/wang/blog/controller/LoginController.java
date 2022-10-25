package com.wang.blog.controller;

import com.wang.blogsystem.common.Result;
import com.wang.blogsystem.common.ResultCode;

import com.wang.blogsystem.dto.LoginDto;
import com.wang.blogsystem.service.LoginService;
import com.wf.captcha.SpecCaptcha;
import com.wf.captcha.base.Captcha;
import com.wf.captcha.utils.CaptchaUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.util.UUID;

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


    @GetMapping("/getCode")
    public Result getCode(HttpServletRequest request, HttpServletResponse response) throws IOException, FontFormatException {
        return loginService.getCode(request,response);
    }
}
