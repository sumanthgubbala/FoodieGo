package com.sumanth.FoodieGo.Mapper;

import com.sumanth.FoodieGo.Dto.MenuItemsResponseDto;
import com.sumanth.FoodieGo.Entity.MenuItem;
import org.springframework.stereotype.Component;

@Component
public class MenuItemsResponse {

    public MenuItemsResponseDto convertToMenuItemsResponse(MenuItem menuItem){
        MenuItemsResponseDto responseDto = new MenuItemsResponseDto();
        responseDto.setId(menuItem.getId());
        responseDto.setName(menuItem.getName());
        responseDto.setPrice(menuItem.getPrice());
        responseDto.setAvailable(menuItem.isAvailable());
        responseDto.setDescription(menuItem.getDescription());
        responseDto.setCategoryName(menuItem.getCategory().getName());
        responseDto.setRestaurantName(menuItem.getRestaurant().getName());
        responseDto.setImgUrl(menuItem.getImgUrl());

        return responseDto;
    }
}
