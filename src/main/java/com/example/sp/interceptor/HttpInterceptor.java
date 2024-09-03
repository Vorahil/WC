package com.example.sp.interceptor;

import com.example.sp.config.Register;
import com.example.sp.pojo.entity.User;
import com.example.sp.pojo.response.ReturnCode;
import com.example.sp.pojo.response.exception.CustomException;
import com.example.sp.utils.JWTUtil;
import com.example.sp.utils.JsonUtil;
import com.example.sp.pojo.response.exception.AuthorityException;
import com.example.sp.annotation.Role;
import com.auth0.jwt.interfaces.Claim;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.Optional;

@Component
public class HttpInterceptor implements HandlerInterceptor {
    public static ThreadLocal<User> userHolder = new ThreadLocal<>();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof ResourceHttpRequestHandler) {
            return true;
        }
        Method method = ((HandlerMethod) handler).getMethod();
        String token = request.getHeader("TOKEN");
        Role role = Register.Role_Map.get(method.getName());
        if (role == null) {
            return true;
        } else {
            User user = null;
            switch (role) {
                /* 鉴权状态可拓展 */
                case DEFAULT:
                    Map<String, Claim> map = JWTUtil.getClaims(token);
                    String userJson = map.get("user").asString();
                    user = JsonUtil.fromJson(userJson, User.class);
                    break;
                case ADMIN:
                    map = JWTUtil.getClaims(token);
                    userJson = map.get("user").asString();
                    user = JsonUtil.fromJson(userJson, User.class);
                    if (user.getRole() != 1) {
                        throw new AuthorityException(ReturnCode.USERNAME_OR_PASSWORD_ERROR);
                    }
            }
            if (user == null) {
                throw new CustomException(ReturnCode.RC401);
            }
            userHolder.set(user);
            return true;
        }
    }
}

