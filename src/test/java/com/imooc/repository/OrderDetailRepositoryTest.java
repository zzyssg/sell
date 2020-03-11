package com.imooc.repository;

import com.imooc.dataobject.OrderDetail;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDetailRepositoryTest {

    @Autowired
    private OrderDetailRepository repository;

    @Test
    public void saveTest() {
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setDetailId("112");
        orderDetail.setOrderId("12");
        orderDetail.setProductId("123455");
        orderDetail.setProductName("皮皮虾");
        orderDetail.setProductPrice(new BigDecimal(12.2));
        orderDetail.setProductQuantity(4);
        OrderDetail orderDetail1 = repository.save(orderDetail);
        Assert.assertEquals(Integer.valueOf(4), orderDetail.getProductQuantity());
    }

    @Test
    public void findByOrder() {
        List<OrderDetail> list = repository.findByOrderId("12");
        Assert.assertEquals(1, list.size());
    }
}