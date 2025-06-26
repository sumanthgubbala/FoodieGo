package com.sumanth.FoodieGo.Repository;

import com.sumanth.FoodieGo.Entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {

    List<Restaurant> findByOwner_Id(Long userId);

}
