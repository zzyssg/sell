package com.imooc.service.impl;

import com.imooc.ProductStatusEnum;
import com.imooc.dataobject.ProductInfo;
import com.imooc.dto.CartDTO;
import com.imooc.enums.ResultEnum;
import com.imooc.exception.SellException;
import com.imooc.repository.ProductInfoRepository;
import com.imooc.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductInfoImpl implements ProductService {
    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Override
    public ProductInfo findOne(String productId) {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId(productId);
        Example<ProductInfo> example = Example.of(productInfo);
        return productInfoRepository.findOne(example).orElse(null);
    }

    @Override
    public List<ProductInfo> findUpAll() {
        return productInfoRepository.findByProductStatus(ProductStatusEnum.UP.getCode());
    }

    @Override
    public Page<ProductInfo> findAll(Pageable pageable) {
        return productInfoRepository.findAll(pageable);
    }

    @Override
    public ProductInfo save(ProductInfo productInfo) {
        return productInfoRepository.save(productInfo);
    }

    @Override
    public void increaseStock(List<CartDTO> cartDTOList) {
        for (CartDTO cartDTO : cartDTOList) {
            ProductInfo productInfo = new ProductInfo();
            productInfo.setProductId(cartDTO.getProductId());
            Example<ProductInfo> example = Example.of(productInfo);
            ProductInfo productInfo1 = productInfoRepository.findOne(example).orElse(null);
            if (productInfo1 == null) {
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }

            Integer res = productInfo1.getProductStock() + cartDTO.getProductQuantity();


            productInfo1.setProductStock(res);
            productInfoRepository.save(productInfo1);
        }
    }

    @Override
    @Transactional
    public void decreaseStock(List<CartDTO> cartDTOList) {
        for (CartDTO cartDTO : cartDTOList) {
            ProductInfo productInfo = new ProductInfo();
            productInfo.setProductId(cartDTO.getProductId());
            Example<ProductInfo> example = Example.of(productInfo);
            ProductInfo productInfo1 = productInfoRepository.findOne(example).orElse(null);
            if (productInfo1 == null) {
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }

            Integer res = productInfo1.getProductStock() - cartDTO.getProductQuantity();
            if (res < 0) {
                throw new SellException(ResultEnum.PRODUCT_STOCK_ERROR);
            }

            productInfo1.setProductStock(res);
            productInfoRepository.save(productInfo1);
        }


    }
}