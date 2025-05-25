package com.example.ecommerce;.service.impl;

import com.example.ecommerce;.entity.OrderItem;
import com.example.ecommerce;.mapper.OrderItemMapper;
import com.example.ecommerce;.service.IOrderItemService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zfy
 * @since 2025-05-20
 */
@Service
public class OrderItemServiceImpl extends ServiceImpl<OrderItemMapper, OrderItem> implements IOrderItemService {

}
