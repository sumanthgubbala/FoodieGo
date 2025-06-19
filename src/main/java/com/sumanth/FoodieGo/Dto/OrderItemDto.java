package com.sumanth.FoodieGo.Dto;

import lombok.Data;

@Data
public class OrderItemDto {
    private int menuItemId;
    private String name;
    private int quantity;
    private double price;
}
