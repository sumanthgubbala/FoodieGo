package com.sumanth.FoodieGo.Dto;

import lombok.Data;

import java.util.List;

@Data
public class RestaurantOrderDto {
    private int restaurantId;
    private double orderTotal;
    private List<OrderItemDto> items;
}
