package com.wang.blogsystem.service.impl;

import com.wang.blogsystem.common.Result;
import com.wang.blogsystem.common.ResultCode;
import com.wang.blogsystem.domain.User;
import com.wang.blogsystem.dto.LoginDto;
import com.wang.blogsystem.dto.UserDto;
import com.wang.blogsystem.mapper.UserMapper;
import com.wang.blogsystem.service.LoginService;
import com.wang.blogsystem.utils.JWTUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author cheng
 * @create 2022-10-13-16:16
 */
@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Autowired
    private UserMapper userMapper;

    private static final String salt = "wangchengBlog";


    @Override
    public List<User> findList(User user) {
        return userMapper.findList(user);
    }

    @Override
    public Result login(LoginDto loginDto) {
        String loginName = loginDto.getLoginName();
        String loginPassWord = loginDto.getLoginPassWord();
        if (StringUtils.isBlank(loginName) || StringUtils.isBlank(loginPassWord)) {
            return Result.error(ResultCode.DATA_IS_EMPTY);
        }
        loginPassWord = DigestUtils.md5Hex(loginPassWord + salt);
        User user = new User();
        user.setUsername(loginName);
        user.setPassword(loginPassWord);
        List<User> list = userMapper.findList(user);
        if (CollectionUtils.isEmpty(list)){
            return Result.error(ResultCode.USER_NOT);
        }
        String token = JWTUtils.createToken(Long.valueOf(list.get(0).getId()));
        redisTemplate.opsForValue().set("token_"+token,list.get(0),1, TimeUnit.HOURS);
        return Result.success(token);
    }


    @Override
    public Result register(UserDto userDto) {
        if (StringUtils.isBlank(userDto.getUsername()) ||
            StringUtils.isBlank(userDto.getPassword()) ||
            StringUtils.isBlank(userDto.getEmail())
        ){
            return Result.error(ResultCode.DATA_IS_EMPTY);
        }
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(DigestUtils.md5Hex(userDto.getPassword()+salt));
        user.setAvatar(user.getAvatar());
        user.setEmail(userDto.getEmail());
        user.setCreateTime(new Date());
        user.setPhone(userDto.getPhone());
        Integer i = userMapper.save(user);
        return null;
    }
}
