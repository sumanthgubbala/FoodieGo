package com.sumanth.FoodieGo.Dto;

import com.sumanth.FoodieGo.enums.OrderStatus;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderResponseDto {

    private int orderId;
    private long userId;
    private int restaurantId;
    private double totalAmount;
    private String deliveryAddress;
    private OrderStatus orderStatus;
    private LocalDateTime placedAt;
    private List<OrderItemDto> orderItems;
}
