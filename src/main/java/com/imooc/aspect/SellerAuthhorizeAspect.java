package com.imooc.aspect;

import com.imooc.constant.CookieConstant;
import com.imooc.constant.RedisConstant;
import com.imooc.exception.SellerAuthorizeException;
import com.imooc.utils.CookieUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
@Slf4j
public class SellerAuthhorizeAspect {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Pointcut("execution(public * com.imooc.controller.Seller*.*(..))"
    + "&& !execution(public * com.imooc.controller.SellerUserController.*(..))")
    public void verify() {

    }

    @Before("verify()")
    private void doVerify() {

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        Cookie cookie = CookieUtil.getCookie(request, CookieConstant.TOKEN);
        if (cookie == null) {
            log.warn("【登录校验错误】" + "cookie中查不到token");
            throw new SellerAuthorizeException();
        }

        //redis中查token
        String tokenValue = stringRedisTemplate.opsForValue().get(String.format(RedisConstant.TOKEN_PREFIX, cookie.getValue()));
        if (tokenValue == null) {
            log.warn("【登录校验错误】" + "redis中查不到token");
            throw new SellerAuthorizeException();
        }
    }

}
