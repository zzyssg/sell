package com.imooc.service;

import com.imooc.dto.OrderDTO;
import com.lly835.bestpay.model.PayResponse;

public interface PayService {
    public PayResponse  create(OrderDTO orderDTO);
}
