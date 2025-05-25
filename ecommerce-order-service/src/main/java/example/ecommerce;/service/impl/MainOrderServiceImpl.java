package com.example.ecommerce.service.impl;

import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.ecommerce.dubbo.ProductDubboService;
import com.example.ecommerce.entity.MainOrder;
import com.example.ecommerce.entity.OrderItem;
import com.example.ecommerce.entity.Product;
import com.example.ecommerce.exception.BizCodeException;
import com.example.ecommerce.mapper.MainOrderMapper;
import com.example.ecommerce.mq.OrderProducer;
import com.example.ecommerce.service.IMainOrderService;
import com.example.ecommerce.service.IOrderItemService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zfy
 * @since 2025-05-20
 */
@Service
public class MainOrderServiceImpl extends ServiceImpl<MainOrderMapper, MainOrder> implements IMainOrderService {

    @DubboReference
    private ProductDubboService productDubboService;

    @Autowired
    private IOrderItemService orderItemService;

    @Override
    @Transactional
    public String submitOrder(Long userId, Long productId, Integer quantity, Byte paymentMethod) throws BizCodeException {
        // 调用库存服务检查商品库存
        Product product = productDubboService.qryProduct(productId);
        if (product == null || product.getStock() < quantity) {
            BizCodeException.publish("库存不足");
        }
        // 创建订单记录、未支付
        this.createOrder(userId, 1, (byte) 0, paymentMethod, product);

        // 发送mq消息通知库存服务预扣减库存, 双方需要约定mq协议字段，这里只传Product回去
        product.setFreezeStock(1);
        OrderProducer.sendOrderMessage("PRODUCT_TOPIC", JSONObject.toJSONString(product));

        // 返回支付二维码
        return "http://127.0.0.1:8083/ecommerce/qr/wechatpay.png";
    }

    private boolean createOrder(Long userId, Integer totalAmount, Byte status, Byte paymentMethod, Product product) {
        MainOrder mainOrder = this.buildMainOrder(userId, totalAmount, status, paymentMethod);
        this.save(mainOrder);
        OrderItem orderItem = this.buildOrderItem(mainOrder.getId(), product, totalAmount);
        orderItemService.save(orderItem);
        return true;
    }

    private MainOrder buildMainOrder(Long userId, Integer totalAmount, Byte status, Byte paymentMethod) {
        MainOrder mainOrder = new MainOrder();
        mainOrder.setUserId(userId);
        mainOrder.setTotalAmount(totalAmount);
        mainOrder.setStatus(status);               // 例如：0=unpaid
        mainOrder.setPaymentMethod(paymentMethod); // 例如：1=微信支付
        mainOrder.setLogisticsId(null);    // 可为null，表示还未发货
        mainOrder.setCreateTime(LocalDateTime.now());
        mainOrder.setUpdateTime(LocalDateTime.now());
        // payTime默认null，表示未支付

        return mainOrder;
    }

    private OrderItem buildOrderItem(Long orderId, Product product, Integer quantity) {
        OrderItem orderItem = new OrderItem();
        orderItem.setOrderId(orderId);
        orderItem.setProductId(product.getId());
        orderItem.setProductName(product.getName());
        //orderItem.setProductSpec(); 需要获取商品属性
        orderItem.setQuantity(quantity);
        orderItem.setPrice(product.getPrice());
        return orderItem;
    }

    @Transactional
    @Override
    public boolean cancelOrder(Long orderId) {
        MainOrder order = getById(orderId);
        if (order == null || order.getStatus() != 0) {
            return false; // 只能取消未支付的订单
        }
        order.setStatus((byte) 4); // 4 = 已取消
        order.setUpdateTime(LocalDateTime.now());
        return updateById(order);
    }

    @Transactional
    @Override
    public boolean updateOrderStatus(Long orderId, Byte status) {
        MainOrder order = getById(orderId);
        if (order == null) return false;
        order.setStatus(status);
        order.setUpdateTime(LocalDateTime.now());
        return updateById(order);
    }
}
