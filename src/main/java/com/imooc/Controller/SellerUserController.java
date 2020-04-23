package com.imooc.controller;

import com.imooc.constant.CookieConstant;
import com.imooc.constant.RedisConstant;
import com.imooc.config.ProjectUrlConfig;
import com.imooc.dataobject.SellerInfo;
import com.imooc.enums.ResultEnum;
import com.imooc.service.impl.SellerInfoServiceImpl;
import com.imooc.utils.CookieUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Controller
@RequestMapping("/seller")
public class SellerUserController {

    @Autowired
    private SellerInfoServiceImpl sellerInfoService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private ProjectUrlConfig projectUrlConfig;

    @GetMapping("/login")
    public ModelAndView login(@RequestParam("openid") String openid,
                              HttpServletResponse response,
                              Map<String, Object> map) {

        //1和数据库里的openid做匹配
        SellerInfo sellerInfo = sellerInfoService.findSellerInfoByOpenid(openid);
        if (sellerInfo == null) {
            map.put("msg", ResultEnum.LOGIN_FAILED);
            map.put("url", "/sell/seller/order/list");
            return new ModelAndView("common/error", map);
        }


        //2设置token至redis

        String token = UUID.randomUUID().toString();
        Integer expire = RedisConstant.EXPIRE;
        stringRedisTemplate.opsForValue().set(String.format(RedisConstant.TOKEN_PREFIX, token), openid, expire, TimeUnit.SECONDS);

        //3设置token到cookie
        CookieUtil.setCookie(response, CookieConstant.TOKEN, token, expire);

        //登录完成后，仅跳转，而不是进行模板的渲染
        return new ModelAndView("redirect:" + projectUrlConfig.getSell() + "/sell/seller/order/list");


    }

    @GetMapping("/logout")
    public ModelAndView logout(HttpServletRequest request,
                       HttpServletResponse response,
                       Map<String, Object> map) {
        Cookie cookie = CookieUtil.getCookie(request, CookieConstant.TOKEN);
        if (cookie != null) {
            //清除缓存
            stringRedisTemplate.opsForValue().getOperations().delete(String.format(RedisConstant.TOKEN_PREFIX, cookie.getValue()));

            //清除cookie
            CookieUtil.setCookie(response, CookieConstant.TOKEN, null, 0);
        }
        map.put("msg",ResultEnum.LOGOUT_SUCCESS);
        map.put("url","/sell/seller/order/list");
        return new ModelAndView("common/success1",map);
    }

}
