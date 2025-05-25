package com.example.ecommerce.dubbo.impl;

import com.example.ecommerce.config.SpringContext;
import com.example.ecommerce.dubbo.ProductDubboService;
import com.example.ecommerce.entity.Product;
import com.example.ecommerce.service.IProductService;
import org.apache.dubbo.config.annotation.DubboService;

@DubboService
public class ProductDubboServiceImpl implements ProductDubboService {

    private IProductService productService = SpringContext.getBean(IProductService.class);

    @Override
    public boolean checkStock(Long productId, Integer quantity) {
        Product product = productService.getById(productId);
        return product.getStock() >= quantity;
    }

    @Override
    public Product qryProduct(Long productId) {
        return productService.getById(productId);
    }
}
