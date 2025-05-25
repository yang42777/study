package com.example.ecommerce.mq;

import com.example.ecommerce.config.SpringContext;
import org.apache.rocketmq.spring.core.RocketMQTemplate;

public class OrderProducer {

    private static RocketMQTemplate rocketMQTemplate;

    // 发送简单消息
    public static void sendOrderMessage(String topic, String msg) {
        if (rocketMQTemplate == null) {
            rocketMQTemplate = SpringContext.getBean(RocketMQTemplate.class);
        }
        rocketMQTemplate.syncSend(topic, msg, 30000);
    }

    // 发送带tag的消息
    public static void sendOrderMessageWithTag(String topic, String tag, String msg) {
        if (rocketMQTemplate == null) {
            rocketMQTemplate = SpringContext.getBean(RocketMQTemplate.class);
        }
        rocketMQTemplate.convertAndSend(topic + ":" + tag, msg);
    }
}