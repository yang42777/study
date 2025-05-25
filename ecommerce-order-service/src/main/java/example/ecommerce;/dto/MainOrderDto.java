package com.example.ecommerce.dto;

import com.example.ecommerce.entity.OrderItem;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 
 * </p>
 *
 * @author zfy
 * @since 2025-05-20
 */
@Data
public class MainOrderDto implements Serializable {

    private Long id;

    private Long userId;

    private BigDecimal totalAmount;

    private Byte status;

    private Byte paymentMethod;

    private Long logisticsId;

    private LocalDateTime payTime;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private List<OrderItem> orderItemList;

    private Integer quantity;

    private Long productId;
}
