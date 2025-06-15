package com.sumanth.FoodieGo.Dto;

import lombok.Data;

@Data
public class CartItemDto {

    private int id;

    private long userId;

    private int menuItemId;

    private int quantity;

    private double price;

    private String restaurantName;

    private String menuItemName;

}
