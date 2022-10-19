package com.wang.blog;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
@MapperScan(basePackages = "com.wang.**.mapper")
@ComponentScan(basePackages = "com.wang")
public class WangChengBlogApplication {

    public static void main(String[] args) {
        SpringApplication.run(WangChengBlogApplication.class, args);
    }

}
