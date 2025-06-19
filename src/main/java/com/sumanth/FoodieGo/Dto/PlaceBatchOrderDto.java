package com.sumanth.FoodieGo.Dto;

import lombok.Data;

import java.util.List;

@Data
public class PlaceBatchOrderDto {

    private long userId;
    private String deliveryAddress;
    private List<CartItemDto> cartItems;
}
