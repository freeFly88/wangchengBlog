package com.wang.blogsystem.dto;

/**
 * @author cheng
 * @create 2022-10-13-16:35
 */
public class LoginDto {

    private  String loginName;

    private  String  loginPassWord;

    private  String  imgCode;

    private  String imgCodeId;


    public String getImgCodeId() {
        return imgCodeId;
    }

    public void setImgCodeId(String imgCodeId) {
        this.imgCodeId = imgCodeId;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getLoginPassWord() {
        return loginPassWord;
    }

    public void setLoginPassWord(String loginPassWord) {
        this.loginPassWord = loginPassWord;
    }

    public String getImgCode() {
        return imgCode;
    }

    public void setImgCode(String imgCode) {
        this.imgCode = imgCode;
    }
}
