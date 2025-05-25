package com.example.ecommerce.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.example.ecommerce.entity.MainOrder;
import com.example.ecommerce.exception.BizCodeException;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zfy
 * @since 2025-05-20
 */
public interface IMainOrderService extends IService<MainOrder> {

    String submitOrder(Long userId, Long productId, Integer quantity, Byte paymentMethod) throws BizCodeException;

    boolean cancelOrder(Long orderId);

    boolean updateOrderStatus(Long orderId, Byte status);

}
