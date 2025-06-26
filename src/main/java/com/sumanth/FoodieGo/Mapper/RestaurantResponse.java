package com.sumanth.FoodieGo.Mapper;

import com.sumanth.FoodieGo.Dto.RestaurantResponseDto;
import com.sumanth.FoodieGo.Entity.Restaurant;
import com.sumanth.FoodieGo.Entity.User;
import com.sumanth.FoodieGo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RestaurantResponse {

    @Autowired
    private UserService userService;

    public RestaurantResponseDto mapToDto(Restaurant restaurant){
        RestaurantResponseDto responseDto = new RestaurantResponseDto();
        responseDto.setId(restaurant.getId());
        responseDto.setName(restaurant.getName());
        responseDto.setEmail(restaurant.getEmail());
        responseDto.setPhoneNumber(restaurant.getPhoneNumber());
        responseDto.setAddress(restaurant.getAddress());
        responseDto.setOpeningTime(restaurant.getOpeningTime());
        responseDto.setClosingTime(restaurant.getClosingTime());
        responseDto.setImgUrl(restaurant.getImgUrl());
        responseDto.setStatus(restaurant.getStatus());
        responseDto.setOwnerId(restaurant.getOwner().getId());
        return responseDto;
    }

    public Restaurant mapToModel(RestaurantResponseDto dto){
        Restaurant restaurant = new Restaurant();

        User owner = this.userService.getByUserId(dto.getOwnerId());
        restaurant.setOwner(owner);

        restaurant.setName(dto.getName());
        restaurant.setAddress(dto.getAddress());
        restaurant.setPhoneNumber(dto.getPhoneNumber());
        restaurant.setEmail(dto.getEmail());
        restaurant.setImgUrl(dto.getImgUrl());
        restaurant.setOpeningTime(dto.getOpeningTime());
        restaurant.setClosingTime(dto.getClosingTime());
        restaurant.setStatus(dto.getStatus());

        return restaurant;
    }
}
