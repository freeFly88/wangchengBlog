package com.wang.blogsystem.utils;
import java.util.UUID;
/**
 * 产生uuid随机字符串工具类。
 */
public class UuidUtils {
    private UuidUtils(){}
    public static String getUuid(){
        return UUID.randomUUID().toString().replace("-", "");
    }

    public  static void main(String[] args){//测试使用
        System.out.println(UuidUtils.getUuid());
        System.out.println(UuidUtils.getUuid());
        System.out.println(UuidUtils.getUuid());
    }
}
