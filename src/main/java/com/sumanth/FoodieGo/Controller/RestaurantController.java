package com.sumanth.FoodieGo.Controller;

import com.sumanth.FoodieGo.Entity.Restaurant;
import com.sumanth.FoodieGo.Service.RestaurantService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/restaurant")
public class RestaurantController {

    private final RestaurantService restaurantService;

    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> create(@RequestBody Restaurant restaurant){
        return ResponseEntity.ok(this.restaurantService.createRestaurant(restaurant));
    }

    @GetMapping("/{restaurantId}")
    public ResponseEntity<?> getByRestaurantId(@PathVariable int restaurantId){
        try{
            return ResponseEntity.ok(this.restaurantService.getByRestaurantId(restaurantId));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("message",e.getMessage()));
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(this.restaurantService.getAllRestaurants());
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
}
