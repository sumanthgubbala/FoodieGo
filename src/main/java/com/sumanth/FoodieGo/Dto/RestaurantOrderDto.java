package com.sumanth.FoodieGo.Dto;

import com.sumanth.FoodieGo.enums.OrderStatus;
import lombok.Data;

import java.util.List;

@Data
public class RestaurantOrderDto {
    private int restaurantId;
    private double orderTotal;
    private List<OrderItemDto> items;
    private OrderStatus orderStatus;
}
