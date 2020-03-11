package com.imooc.repository;

import com.imooc.dataobject.ProductInfo;
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
public class ProductInfoRepositoryTest {

    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Test
    public void saveTest() {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setCategoryType(2);
        productInfo.setProductDescription("很好喝");
        productInfo.setProductIcon(",,,");
        productInfo.setProductName("皮蛋粥");
        productInfo.setProductId("123456");
        productInfo.setProductPrice(new BigDecimal(3.2));
        productInfo.setProductStock(100);
        productInfo.setProductStatus(1);

        ProductInfo res = productInfoRepository.save(productInfo);
        Assert.assertNotNull(res);

    }

    @Test
    public void findByProductStatus() {
        List<ProductInfo> list = productInfoRepository.findByProductStatus(1);
        Assert.assertEquals(1, list.size());
    }
}