package com.sumanth.FoodieGo.Dto;

import lombok.Data;

@Data
public class MenuItemsResponseDto {

    private int id;

    private String name;

    private String restaurantName;

    private String categoryName;

    private boolean available;

    private double price;

    private String description;

    private String imgUrl;
}
