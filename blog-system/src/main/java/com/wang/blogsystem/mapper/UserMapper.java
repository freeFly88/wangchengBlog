package com.wang.blogsystem.mapper;

import com.wang.blogsystem.domain.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author cheng
 * @create 2022-10-13-18:08
 */
@Mapper
public interface UserMapper {

    List<User> findList(User user);

    List<User> page(User user);

    Integer save(User user);
}
