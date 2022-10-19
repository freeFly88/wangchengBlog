package com.wang.blogframework.Interceptor;

import com.aliyun.oss.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class JwtTokenInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    	// 在拦截器中，如果请求为OPTIONS请求，则返回true，表示可以正常访问，然后就会收到真正的GET/POST请求
    	if (HttpMethod.OPTIONS.toString().equals(request.getMethod())) {
		    System.out.println("OPTIONS请求，放行");
		    return true;
		}
        // 验证token
        boolean token = JwtUtils.checkToken(request.getHeader("SET_TOKEN"));
    	if (token){
    	    return true;
        }else {
            // 验证不通过，返回401，表示用户未登录
            response.setStatus(401);
        }
        return false;
    }

}
