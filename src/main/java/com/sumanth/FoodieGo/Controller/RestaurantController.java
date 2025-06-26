package com.sumanth.FoodieGo.Controller;

import com.sumanth.FoodieGo.Dto.RestaurantResponseDto;
import com.sumanth.FoodieGo.Entity.Restaurant;
import com.sumanth.FoodieGo.Mapper.RestaurantResponse;
import com.sumanth.FoodieGo.Service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/restaurant")
public class RestaurantController {

    private final RestaurantService restaurantService;

    @Autowired
    private RestaurantResponse restaurantResponse;

    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> create(@RequestBody RestaurantResponseDto dto){
        Restaurant restaurant = this.restaurantResponse.mapToModel(dto);
        return ResponseEntity.ok(this.restaurantService.createRestaurant(restaurant));
    }

    @GetMapping("/{restaurantId}")
    public ResponseEntity<?> getByRestaurantId(@PathVariable int restaurantId){
        try{
            Restaurant restaurant = this.restaurantService.getByRestaurantId(restaurantId);
            RestaurantResponseDto responseDto = this.restaurantResponse.mapToDto(restaurant);
            return ResponseEntity.ok(responseDto);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("message",e.getMessage()));
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAll(){
        List<Restaurant> restaurants = this.restaurantService.getAllRestaurants();
        List<RestaurantResponseDto> responseDtos = new ArrayList<>();
        for (Restaurant restaurant : restaurants){
            RestaurantResponseDto dto = this.restaurantResponse.mapToDto(restaurant);
            responseDtos.add(dto);
        }
        return ResponseEntity.ok(responseDtos);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateRestaurant(@RequestBody Restaurant restaurant){
        try {
            return ResponseEntity.ok(this.restaurantService.updateRestaurant(restaurant));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("Message",e.getMessage()));
        }
    }

    @PutMapping("/image")
    public ResponseEntity<?> updateImage(@RequestBody Restaurant restaurant){
        try{
            return ResponseEntity.ok(this.restaurantService.updateImage(restaurant));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("Message",e.getMessage()));
        }
    }

    @DeleteMapping("/{restaurantId}")
    public ResponseEntity<?> delete(@PathVariable int restaurantId){
        try {
            return ResponseEntity.ok(this.restaurantService.delete(restaurantId));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("message",e.getMessage()));
        }
    }
    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getRestaurantsByUserId(@PathVariable Long userId) {
        List<Restaurant> restaurants = restaurantService.getRestaurantsByUserId(userId);
        List<RestaurantResponseDto> responseDtos= new ArrayList<>();
        for(Restaurant restaurant : restaurants){
            RestaurantResponseDto dto = this.restaurantResponse.mapToDto(restaurant);
            responseDtos.add(dto);
        }
        return ResponseEntity.ok(responseDtos);
    }

}
