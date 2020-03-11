package com.imooc.repository;

import com.imooc.dataobject.OrderMaster;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;


@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterRepositoryTest {

    @Autowired
    private OrderMasterRepository repository;

    private final String OPENID = "111111";

    @Test
    public void save() {
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setBuyerAddress("小学路");
        orderMaster.setBuyerName("小明");
        orderMaster.setBuyerOpenid(OPENID);
        orderMaster.setBuyerPhone("110");
        orderMaster.setOrderAmount(new BigDecimal(111.3));
        orderMaster.setOrderId("13");
        orderMaster.setOrderStatus(0);
        orderMaster.setPayStatus(1);
        OrderMaster res = repository.save(orderMaster);
        Assert.assertEquals("小明",res.getBuyerName());
    }

    @Test
    public void findByBuyerOpenid() {
        PageRequest pageRequest = PageRequest.of(0, 3);
        Page<OrderMaster> res = repository.findByBuyerOpenid(OPENID, pageRequest);
        Assert.assertEquals(2,res.getTotalElements());


    }
}