package com.example.ecommerce.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.ecommerce.entity.OrderItem;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zfy
 * @since 2025-05-20
 */
@Mapper
public interface OrderItemMapper extends BaseMapper<OrderItem> {

}
