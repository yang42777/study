package com.example.ecommerce.mq;

import com.alibaba.fastjson2.JSONObject;
import com.example.ecommerce.entity.Product;
import com.example.ecommerce.service.IProductService;
import com.example.ecommerce.utils.RedisUtil;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RocketMQMessageListener(topic = "PRODUCT_TOPIC", consumerGroup = "order_consumer_group", nameServer = "127.0.0.1:9876")
public class OrderConsumer implements RocketMQListener<String> {

    @Autowired
    private IProductService productService;

    @Transactional
    @Override
    public void onMessage(String message) {
        // 订单处理成功，进行预扣减
        Product product = JSONObject.parseObject(message, Product.class);
        Integer freezeStock = product.getFreezeStock();

        String lockKey = "product:" + product.getId();
        String requestId = UUID.randomUUID().toString();

        if (RedisUtil.tryLock(lockKey, requestId, 30)) {
            try {
                product = productService.getById(product.getId());
                product.setFreezeStock(product.getFreezeStock() + freezeStock);
                product.setStock(product.getStock() - freezeStock);
                productService.updateById(product);
            }
            finally {
                RedisUtil.releaseLock(lockKey, requestId);
            }
        } else {
            // 获取锁失败，进行相应处理
            throw new RuntimeException("系统繁忙，请稍后重试");
        }
    }
}