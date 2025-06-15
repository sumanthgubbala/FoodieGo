package com.sumanth.FoodieGo.Repository;

import com.sumanth.FoodieGo.Entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem,Integer> {

    List<CartItem> findByUser_Id(long userId);
}
