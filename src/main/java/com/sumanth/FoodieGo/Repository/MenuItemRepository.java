package com.sumanth.FoodieGo.Repository;

import com.sumanth.FoodieGo.Entity.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuItemRepository extends JpaRepository<MenuItem ,Integer> {
}
