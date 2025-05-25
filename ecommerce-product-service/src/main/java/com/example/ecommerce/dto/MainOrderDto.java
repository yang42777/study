package com.example.ecommerce.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MainOrderDto {

    private Long id;

    private Long userId;

    private Integer totalAmount;

    private Byte status;

    private Byte paymentMethod;

    private Long logisticsId;

    private LocalDateTime payTime;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

}
