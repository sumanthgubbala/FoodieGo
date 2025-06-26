package com.sumanth.FoodieGo.Repository;

import com.sumanth.FoodieGo.Entity.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuItemRepository extends JpaRepository<MenuItem ,Integer> {

    List<MenuItem> findByCategoryId(int categoryId);

    List<MenuItem> findByRestaurantId(int restaurantId);
}
