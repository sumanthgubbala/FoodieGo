package com.sumanth.FoodieGo.Mapper;

import com.sumanth.FoodieGo.Dto.MenuItemDto;
import com.sumanth.FoodieGo.Entity.Category;
import com.sumanth.FoodieGo.Entity.MenuItem;
import com.sumanth.FoodieGo.Entity.Restaurant;
import com.sumanth.FoodieGo.Service.CategoryService;
import com.sumanth.FoodieGo.Service.RestaurantService;
import org.springframework.stereotype.Component;

@Component
public class MenuItemMapper {

    private final RestaurantService restaurantService;

    private final CategoryService categoryService;

    public MenuItemMapper(RestaurantService restaurantService, CategoryService categoryService) {
        this.restaurantService = restaurantService;
        this.categoryService = categoryService;
    }

    public MenuItem mapToModel(MenuItemDto menuItemDto){
        MenuItem menuItem = new MenuItem();
        try{
            Restaurant restaurant = this.restaurantService.getByRestaurantId(menuItemDto.getRestaurantId());
            menuItem.setRestaurant(restaurant);

            Category category = this.categoryService.getByCategoryId(menuItemDto.getCategoryId());
            menuItem.setCategory(category);

        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
        menuItem.setName(menuItemDto.getName());
        menuItem.setPrice(menuItemDto.getPrice());
        menuItem.setDescription(menuItemDto.getDescription());
        menuItem.setPrice(menuItemDto.getPrice());
        menuItem.setAvailable(menuItemDto.isAvailable());
        menuItem.setImgUrl(menuItemDto.getImgUrl());

        return menuItem;
    }
}
