package com.imooc.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class CookieUtil {
    public static void setCookie(
            HttpServletResponse response, String name, String value, int maxTime) {
        Cookie cookie = new Cookie(name, value);
        cookie.setPath("/");
        cookie.setMaxAge(maxTime);
        response.addCookie(cookie);
    }

    public static Cookie getCookie(HttpServletRequest request,
                                 String name) {
        Map<String, Cookie> cookieMap = getCookieMap(request);
        if (cookieMap.containsKey(name)) {
            return cookieMap.get(name);
        } else {
            return null;
        }

    }

    private static Map<String, Cookie> getCookieMap(HttpServletRequest request) {
        Map<String, Cookie> map = new HashMap<>();

        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                    map.put(cookie.getName(), cookie);

            }
        }
        return map;

    }

}
