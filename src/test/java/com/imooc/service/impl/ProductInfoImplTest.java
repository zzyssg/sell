package com.imooc.service.impl;

import com.imooc.dataobject.ProductInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
import java.util.List;



@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoImplTest {

    @Autowired
    private ProductInfoImpl productInfoimpl;

    @Test
    public void findOne() {
        ProductInfo productInfo = productInfoimpl.findOne("123456");
        Assert.assertEquals("皮蛋粥", productInfo.getProductName());
    }

    @Test
    public void findUpAll() {
        List<ProductInfo> list = productInfoimpl.findUpAll();
        Assert.assertNotNull(list);
    }

    @Test
    public void findAll() {
        PageRequest pr = PageRequest.of(0, 2);
        Page<ProductInfo> productInfos = productInfoimpl.findAll(pr);
    }

    @Test
    public void save() {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setCategoryType(2);
        productInfo.setProductDescription("很好吃");
        productInfo.setProductIcon(",,,");
        productInfo.setProductName("皮皮虾");
        productInfo.setProductId("123455");
        productInfo.setProductPrice(new BigDecimal(3.2));
        productInfo.setProductStock(100);
        productInfo.setProductStatus(1);

        ProductInfo productInfo1 = productInfoimpl.save(productInfo);
        Assert.assertEquals(new Integer(2),productInfo1.getCategoryType());
    }

    @Test
    public void onSale() {
        ProductInfo productInfo = productInfoimpl.onSale("123456");
        Assert.assertEquals("皮蛋粥", productInfo.getProductName());
    }
    @Test
    public void offSale() {
        ProductInfo productInfo = productInfoimpl.offSale("123456");
        Assert.assertEquals("皮蛋粥", productInfo.getProductName());
    }


}