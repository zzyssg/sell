package com.imooc.service.impl;

import com.imooc.dataobject.OrderDetail;
import com.imooc.dataobject.OrderMaster;
import com.imooc.dto.CartDTO;
import com.imooc.dto.OrderDTO;
import com.imooc.enums.OrderStatusEnum;
import com.imooc.enums.PayStatusEnum;
import com.imooc.enums.ResultEnum;
import com.imooc.exception.SellException;
import com.imooc.repository.OrderDetailRepository;
import com.imooc.repository.OrderMasterRepository;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.ast.Or;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OrderServiceImplTest {

    private final String BUYER_OPENID = "89757";

    private final String ORDER_ID = "1583908761441525651";

    @Autowired
    private OrderServiceImpl orderServiceimpl;

    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Test
    public void create() {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName("周杰伦");
        orderDTO.setBuyerAddress("荷兰");
        orderDTO.setBuyerPhone("1110");
        orderDTO.setBuyerOpenid(BUYER_OPENID);

        //购物差
        List<OrderDetail> orderDetailList = new ArrayList<>();
        OrderDetail o1 = new OrderDetail();
        o1.setProductId("123455");
        o1.setProductQuantity(3);
        orderDetailList.add(o1);
        orderDTO.setOrderDetailList(orderDetailList);
        OrderDTO res = orderServiceimpl.create(orderDTO);
        log.info("[订单创建成功] res = {}",res);
        Assert.assertEquals("周杰伦",res.getBuyerName());

    }

    @Test
    public void findOne() {
        OrderDTO orderDTO = orderServiceimpl.findOne(ORDER_ID);
        log.info("findOne :  order = {}", orderDTO);
        Assert.assertEquals("周杰伦",orderDTO.getBuyerName());

    }

    @Test
    public void findList() {
        PageRequest pageable = PageRequest.of(0, 4);
        Page page =  orderServiceimpl.findList(BUYER_OPENID, pageable);
        Assert.assertEquals(3,page.getTotalElements());
    }

    @Test
    public void calcle() {
        OrderDTO orderDTO = orderServiceimpl.findOne(ORDER_ID);
        OrderDTO res = orderServiceimpl.calcle(orderDTO);
        Assert.assertEquals(OrderStatusEnum.CANCLE.getCode(),res.getOrderStatus());

    }

    @Test
    public void finish() {
        OrderDTO orderDTO = orderServiceimpl.findOne(ORDER_ID);
        OrderDTO res = orderServiceimpl.finish(orderDTO);
        Assert.assertEquals(OrderStatusEnum.FINISH.getCode(),res.getOrderStatus());

    }

    @Test
    public void paid() {
        OrderDTO orderDTO = orderServiceimpl.findOne(ORDER_ID);
        OrderDTO res = orderServiceimpl.paid(orderDTO);
        Assert.assertEquals(PayStatusEnum.FINISH.getCode(),res.getPayStatus());
    }
}