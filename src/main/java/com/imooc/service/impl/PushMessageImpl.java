package com.imooc.service.impl;

import com.imooc.dto.OrderDTO;
import com.imooc.service.PushService;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PushMessageImpl implements PushService {

    @Autowired
    private WxMpService wxMpService;

    @Override
    public void orderStatus(OrderDTO orderDTO) {
        WxMpTemplateMessage wxMpTemplateMessage = new WxMpTemplateMessage();
        //TODO
        try {
            wxMpService.getTemplateMsgService().sendTemplateMsg(wxMpTemplateMessage);
        } catch (WxErrorException e) {
            log.error("微信推送消息失败“" + e.getMessage());
        }
    }
}
