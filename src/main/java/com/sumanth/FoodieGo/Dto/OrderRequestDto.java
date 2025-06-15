package com.sumanth.FoodieGo.Dto;

import lombok.Data;

import java.util.List;

@Data
public class OrderRequestDto {
    private long userId;
    private int restaurantId;
    private String deliveryAddress;
    private List<OrderItemDto> orderItems;
}
