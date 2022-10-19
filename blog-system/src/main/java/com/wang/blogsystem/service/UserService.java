package com.wang.blogsystem.service;

import com.github.pagehelper.PageInfo;
import com.wang.blogsystem.domain.User;

/**
 * @author cheng
 * @create 2022-10-13-18:04
 */
public interface UserService {

    PageInfo page(Integer pageNum, Integer pageSize, User user);
}
