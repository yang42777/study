package com.example.ecommerce.dubbo;

import com.example.ecommerce.entity.Product;

public interface ProductDubboService {

    boolean checkStock(Long productId, Integer quantity);

    Product qryProduct(Long productId);

}
