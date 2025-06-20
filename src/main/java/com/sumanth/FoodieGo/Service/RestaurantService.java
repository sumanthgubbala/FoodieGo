package com.sumanth.FoodieGo.Service;

import com.sumanth.FoodieGo.Entity.Restaurant;
import com.sumanth.FoodieGo.Repository.RestaurantRepository;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    public RestaurantService(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    public Restaurant createRestaurant(Restaurant restaurant){
        return this.restaurantRepository.save(restaurant);
    }

    public Restaurant getByRestaurantId(int restaurantId){
        return this.restaurantRepository.findById(restaurantId).orElseThrow(
                () -> new RuntimeException("Restaurant Id not found")
        );
    }

    public List<Restaurant> getAllRestaurants(){
        return this.restaurantRepository.findAll();
    }

    public Restaurant updateRestaurant(Restaurant restaurant){
        Restaurant existing = getByRestaurantId(restaurant.getId());
        existing.setName(restaurant.getName());
        existing.setAddress(restaurant.getAddress());
        existing.setEmail(restaurant.getEmail());
        existing.setPhoneNumber(restaurant.getPhoneNumber());
        existing.setOpeningTime(restaurant.getOpeningTime());
        existing.setClosingTime(restaurant.getClosingTime());
        existing.setStatus(restaurant.getStatus());
        existing.setImgUrl(restaurant.getImgUrl());
        return this.restaurantRepository.save(existing);
    }

    public Restaurant updateImage(Restaurant restaurant){
        Restaurant existing = getByRestaurantId(restaurant.getId());
        existing.setImgUrl(restaurant.getImgUrl());
        restaurantRepository.save(existing);
        return existing;
    }

    public String delete(int restaurantId){
        Restaurant restaurant = getByRestaurantId(restaurantId);
        this.restaurantRepository.delete(restaurant);
        return "Deleted Successfully";
    }
}
