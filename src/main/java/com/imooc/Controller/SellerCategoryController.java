package com.imooc.controller;

import com.imooc.dataobject.ProductCategory;
import com.imooc.enums.ResultEnum;
import com.imooc.exception.SellException;
import com.imooc.form.CategoryForm;
import com.imooc.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/seller/category")
@Slf4j
public class SellerCategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/list")
    public ModelAndView list(Map<String, Object> map) {
        List<ProductCategory> categoryList = categoryService.findAll();
        System.out.println(categoryList.size());
        map.put("categoryList", categoryList);
        return new ModelAndView("category/list", map);

    }

    @GetMapping("/index")
    public ModelAndView index(@RequestParam(value = "categoryId", required = false) Integer categoryId,
                              Map<String, Object> map) {
        ProductCategory productCategory;
        if (categoryId != null) {
            //根据类别查找类目的实体
            try {
                productCategory = categoryService.findOne(categoryId);
                map.put("category", productCategory);
            } catch (SellException e) {
                map.put("msg", ResultEnum.PRODUCT_CATRGORY_NOT_EXIST);
                return new ModelAndView("common/error", map);
            }
        }
        return new ModelAndView("category/index", map);


    }

    @PostMapping("/save")
    public ModelAndView save(@Valid CategoryForm categoryForm,
                             BindingResult bindingResult,
                             Map<String, Object> map) {
        if (bindingResult.hasErrors()) {
            map.put("msg", ResultEnum.FORM_DATA_ERROR);
            map.put("url", "/sell/seller/category/index");
            return new ModelAndView("common/error", map);
        }
        ProductCategory productCategory = new ProductCategory();
        try {
            if (categoryForm.getCategoryId() != null) {
                productCategory = categoryService.findOne(categoryForm.getCategoryId());
            }
            BeanUtils.copyProperties(categoryForm, productCategory);
            categoryService.save(productCategory);
        } catch (SellException e) {
            map.put("msg", ResultEnum.PRODUCT_CATRGORY_NOT_EXIST);
            map.put("url", "/sell/seller/category/index");
        }
        map.put("url","/sell/seller/category/list");
        return new ModelAndView("common/success1", map);

    }

}
