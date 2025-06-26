package com.sumanth.FoodieGo.Mapper;

import com.sumanth.FoodieGo.Dto.CartItemDto;
import com.sumanth.FoodieGo.Entity.CartItem;
import com.sumanth.FoodieGo.Entity.MenuItem;
import com.sumanth.FoodieGo.Entity.Restaurant;
import com.sumanth.FoodieGo.Entity.User;
import com.sumanth.FoodieGo.Service.MenuItemService;
import com.sumanth.FoodieGo.Service.UserService;
import org.springframework.stereotype.Component;

@Component
public class CartItemMapper {

    private final UserService userService;

    private final MenuItemService menuItemService;

    public CartItemMapper(UserService userService, MenuItemService menuItemService) {
        this.userService = userService;
        this.menuItemService = menuItemService;
    }

    public CartItem mapToModel(CartItemDto cartItemDto){
        CartItem cartItem = new CartItem();

        User user = this.userService.getByUserId(cartItemDto.getUserId());
        cartItem.setUser(user);

        MenuItem menuItem = this.menuItemService.getByMenuItemId(cartItemDto.getMenuItemId());
        cartItem.setMenuItem(menuItem);

        cartItem.setQuantity(cartItemDto.getQuantity());



        return cartItem;
    }

    public CartItemDto modelToDto(CartItem cartItem){
        CartItemDto cartItemDto = new CartItemDto();

        cartItemDto.setId(cartItem.getId());

        MenuItem menuItem = cartItem.getMenuItem();
        cartItemDto.setMenuItemId(menuItem.getId());
        cartItemDto.setMenuItemName(menuItem.getName());
        double price = menuItem.getPrice() * cartItem.getQuantity();
        cartItemDto.setPrice(price);
        cartItemDto.setImgUrl(cartItem.getMenuItem().getImgUrl());

        Restaurant restaurant = menuItem.getRestaurant();
        cartItemDto.setRestaurantName(restaurant.getName());

        cartItemDto.setQuantity(cartItem.getQuantity());
        User user = cartItem.getUser();
        cartItemDto.setUserId(user.getId());


        return cartItemDto;
    }
}
