package com.imooc.service.impl;

import com.imooc.dataobject.ProductCategory;
import com.imooc.repository.ProductCategoryRepository;
import com.imooc.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Override
    public List<ProductCategory> findAll() {
        return productCategoryRepository.findAll();
    }

    @Override
    public ProductCategory findOne(Integer categoryId) {
        return productCategoryRepository.getOne(categoryId);
    }

    @Override
    public List<ProductCategory> findByCategoryTypeIn(List<Integer> list) {
        return productCategoryRepository.findByCategoryTypeIn(list);
    }

    @Override
    public ProductCategory save(ProductCategory productCategory) {
        return productCategoryRepository.save(productCategory);
    }
}
