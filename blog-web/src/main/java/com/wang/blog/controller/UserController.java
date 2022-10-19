package com.wang.blog.controller;

import com.wang.blogsystem.common.Result;
import com.wang.blogsystem.common.SynProperties;
import com.wang.blogsystem.domain.User;
import com.wang.blogsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author cheng
 * @create 2022-10-14-9:58
 */
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;



    @RequestMapping("/page")
    public Result page(
            @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize,
            @RequestBody User user
    ){
        return Result .success(userService.page(pageNum,pageSize,user));
    }

}
