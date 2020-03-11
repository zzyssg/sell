package com.imooc.repository;


import com.imooc.dataobject.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryRepositoryTest {
    @Autowired
    private ProductCategoryRepository productCategoryRepository;
    @Test
    public void findOneTest() {
        ProductCategory productCategory = productCategoryRepository.getOne(1);
        System.out.println(productCategory.toString());
    }
    @Test
    @Transactional
    public void saveOneTest() {
        ProductCategory productCategory = new ProductCategory("男生最爱",5);
        ProductCategory res = productCategoryRepository.save(productCategory);
        Assert.assertNotNull(res);

    }
    @Test
    public void findByCategoryTypeTest() {
        List<ProductCategory> list = productCategoryRepository.findByCategoryTypeIn(Arrays.asList(2, 4));
        Assert.assertNotEquals(0, list.size());

    }

}