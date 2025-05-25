package com.example.ecommerce.mq;

import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

@Service
@RocketMQMessageListener(topic = "order-topic", consumerGroup = "order_consumer_group")
public class OrderConsumer implements RocketMQListener<String> {

    @Override
    public void onMessage(String message) {
        System.out.println("收到订单消息: " + message);
        // 业务处理逻辑写这里
    }
}