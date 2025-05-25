package com.example.ecommerce.service.impl

-product-service.service.impl;

import com.example.ecommerce.ecommerce-product-service.entity.Product;
import com.example.ecommerce.ecommerce-product-service.mapper.ProductMapper;
import com.example.ecommerce.ecommerce-product-service.service.IProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zfy
 * @since 2025-05-19
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements IProductService {

}
