package com.imooc.service.impl;

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

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryServiceImplTest {
    @Autowired
    private CategoryServiceImpl categoryService;

    @Test
    public void findAll() {
        List<ProductCategory> list = categoryService.findAll();
        Assert.assertNotEquals(0, list.size());
    }

    @Test
    public void findOne() {
        ProductCategory productCategory = categoryService.findOne(1);
        Assert.assertEquals(Integer.valueOf(2),productCategory.getCategoryType());
    }

    @Test
    public void findByCategoryTypeIn() {
        List<ProductCategory> list = categoryService.findByCategoryTypeIn(Arrays.asList(2,4));
        Assert.assertEquals(2, list.size());
    }

    @Test
    @Transactional
    public void save() {
        ProductCategory productCategory1 = new ProductCategory("都喜欢",3);
        ProductCategory productCategory = categoryService.save(productCategory1);
        Assert.assertNotNull(productCategory);
    }
}