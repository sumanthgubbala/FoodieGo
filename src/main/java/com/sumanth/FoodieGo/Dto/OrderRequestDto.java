package com.sumanth.FoodieGo.Dto;

import com.sumanth.FoodieGo.enums.OrderStatus;
import lombok.Data;

import java.util.List;

@Data
public class OrderRequestDto {
    private int id;
    private long userId;
    private int restaurantId;
    private String deliveryAddress;
    private OrderStatus orderStatus;
    private List<OrderItemDto> orderItems;
}
