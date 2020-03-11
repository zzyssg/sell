package com.imooc.service;

import com.imooc.dataobject.ProductCategory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService {
    List<ProductCategory> findAll();

    ProductCategory findOne(Integer categoryId);

    List<ProductCategory> findByCategoryTypeIn(List<Integer> list);

    ProductCategory save(ProductCategory productCategory);

}
