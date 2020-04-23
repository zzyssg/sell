package com.imooc.handler;

import com.imooc.config.ProjectUrlConfig;
import com.imooc.exception.SellerAuthorizeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class SellerExceptionHandler {

    @Autowired
    private ProjectUrlConfig projectUrlConfig;

    //拦截登录异常
    @ExceptionHandler(value= SellerAuthorizeException.class)
    public ModelAndView handlerSellerAuthorize() {
        return new ModelAndView("redirect:"
                        .concat(projectUrlConfig.getWeChatOpenAuthorize()
                        .concat("/sell/wechat/qrAuthorize")
                        .concat("?returnUrl=")
                        .concat(projectUrlConfig.getSell())
                        .concat("/sell/seller/login"))
        );
    }


}
