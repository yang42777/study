package com.example.ecommerce.service.impl

-product-service.service.impl;

import com.example.ecommerce.ecommerce-product-service.entity.ProductCategory;
import com.example.ecommerce.ecommerce-product-service.mapper.ProductCategoryMapper;
import com.example.ecommerce.ecommerce-product-service.service.IProductCategoryService;
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
public class ProductCategoryServiceImpl extends ServiceImpl<ProductCategoryMapper, ProductCategory> implements IProductCategoryService {

}
