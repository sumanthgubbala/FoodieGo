package com.sumanth.FoodieGo.Repository;

import com.sumanth.FoodieGo.Entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,Integer> {

    List<Order> findByRestaurantId(int restaurantId);
}
