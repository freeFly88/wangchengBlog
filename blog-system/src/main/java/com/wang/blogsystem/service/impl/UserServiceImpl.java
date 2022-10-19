package com.wang.blogsystem.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wang.blogsystem.domain.User;
import com.wang.blogsystem.mapper.UserMapper;
import com.wang.blogsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @author cheng
 * @create 2022-10-13-18:05
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public PageInfo page(Integer pageNum, Integer pageSize, User user) {
        PageHelper.startPage(pageNum,pageSize);
        List<User> page = userMapper.page(user);
        return new PageInfo<>(page);
    }


}
