package com.imooc.service.impl;

import com.imooc.dataobject.SellerInfo;
import com.imooc.repository.SellerInfoRepository;
import com.imooc.service.SellerInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellerInfoServiceImpl implements SellerInfoService {

    @Autowired
    private SellerInfoRepository sellerInfoRepository;


    @Override
    public SellerInfo findSellerInfoByOpenid(String openid) {
        SellerInfo sellerInfo = sellerInfoRepository.findByOpenid(openid);
        return sellerInfo;
    }
}
