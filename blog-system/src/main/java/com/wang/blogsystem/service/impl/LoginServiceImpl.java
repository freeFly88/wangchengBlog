package com.wang.blogsystem.service.impl;

import cn.hutool.captcha.CircleCaptcha;
import com.wang.blogsystem.common.Result;
import com.wang.blogsystem.common.ResultCode;
import com.wang.blogsystem.domain.User;
import com.wang.blogsystem.dto.LoginDto;
import com.wang.blogsystem.dto.UserDto;
import com.wang.blogsystem.mapper.UserMapper;
import com.wang.blogsystem.service.LoginService;
import com.wang.blogsystem.utils.JWTUtils;
import com.wang.blogsystem.utils.RedisClient;
import com.wang.blogsystem.utils.UuidUtils;
import com.wf.captcha.SpecCaptcha;
import com.wf.captcha.base.Captcha;
import com.wf.captcha.utils.CaptchaUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author cheng
 *
 */
@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private RedisClient redisClient;

    @Autowired
    private UserMapper userMapper;

    // 盐 用来加密密码
    private static final String salt = "wangchengBlog";


    @Override
    public List<User> findList(User user) {
        return userMapper.findList(user);
    }


    /**
     * 登录
     * @param loginDto
     * @return
     */
    @Override
    public Result login(LoginDto loginDto) {
        String loginName = loginDto.getLoginName();
        String loginPassWord = loginDto.getLoginPassWord();
        String imgCodeId = loginDto.getImgCodeId();
        String imgCode = loginDto.getImgCode();
        if (StringUtils.isBlank(imgCode) || StringUtils.isBlank(imgCodeId)){
            return Result.error(ResultCode.CODE_ERROR);
        }
        if (!checkCode(imgCode,imgCodeId)){
            return Result.error(ResultCode.CODE_ERROR_EMPTY);
        }
        if (StringUtils.isBlank(loginName) || StringUtils.isBlank(loginPassWord)) {
            return Result.error(ResultCode.LOGIN_ERROR_EMPTY);
        }
        //对密码加密
        loginPassWord = DigestUtils.md5Hex(loginPassWord + salt);
        User user = new User();
        user.setUsername(loginName);
        user.setPassword(loginPassWord);
        List<User> list = userMapper.findList(user);
        if (CollectionUtils.isEmpty(list)){
            return Result.error(ResultCode.USER_NOT);
        }
        // todo 生成token返回前端 保存到redis中 有效期1小时
        String token = JWTUtils.createToken(Long.valueOf(list.get(0).getId()));
        redisClient.set("token_"+token,list.get(0),3600);
        return Result.success(token);
    }


    /**
     * 注册
     * @param userDto
     * @return
     */
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
        if (i>0){
            return Result.success(i);
        }else {
            return Result.error(ResultCode.REGISTER_ERROR);
        }
    }


    @Override
    public Result getCode(HttpServletRequest request, HttpServletResponse response) throws IOException, FontFormatException {
        SpecCaptcha specCaptcha = new SpecCaptcha(130, 48, 4);
        specCaptcha.setFont(Captcha.FONT_1);
        String id = UUID.randomUUID().toString();
        response.setHeader("id", id);
        CaptchaUtil.out(specCaptcha, request, response);
        String verCode = specCaptcha.text().toLowerCase();
        redisClient.set(id,verCode,300);
        return Result.success();
    }

    //校验验证码
    public Boolean checkCode(String imgCode,String imgCodeId){
        Object object = redisClient.get(imgCodeId);
        if (null == object){
            return false;
        }
        String code = object.toString();
        if (!code.equals(imgCode)){
            return false;
        }
        return true;
    }
}
