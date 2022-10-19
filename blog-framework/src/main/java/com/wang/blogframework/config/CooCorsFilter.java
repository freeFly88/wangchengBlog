package com.wang.blogframework.config;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
 * 自定义拦截器
 *
 * @author lvhuimin
 * @version 2019/12/21
 */
@Order(1)
@Component
@WebFilter(urlPatterns = "/*", filterName = "cooCorsFilter")
public class CooCorsFilter implements Filter {

    String ORIGIN = "Origin";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        String origin = httpRequest.getHeader(ORIGIN);

        httpResponse.setCharacterEncoding("UTF-8");
        httpResponse.setContentType("application/json; charset=utf-8");
        httpResponse.setHeader("Access-Control-Allow-Origin", origin);
        httpResponse.setHeader("Access-Control-Allow-Credentials", "true");
        httpResponse.setHeader("Access-Control-Allow-Methods", "*");
        // 如果允许所有header,就用*
        httpResponse.setHeader("Access-Control-Allow-Headers", "Content-Type,Authorization,token,userId,sysCode,requestSourceType");
        httpResponse.setHeader("Access-Control-Expose-Headers", "*");
        filterChain.doFilter(httpRequest, httpResponse);
    }

    @Override
    public void destroy() {

    }

}
