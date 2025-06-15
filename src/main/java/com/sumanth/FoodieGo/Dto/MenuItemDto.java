package com.sumanth.FoodieGo.Dto;

import lombok.Data;

@Data
public class MenuItemDto {


    private int id;

    private int restaurantId;

    private int categoryId;

    private String name;

    private String description;

    private double price;

    private boolean available;

    private String imgUrl;
}
