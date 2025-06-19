package com.sumanth.FoodieGo.Repository;

import com.sumanth.FoodieGo.Entity.BatchOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BatchOrderRepository extends JpaRepository<BatchOrder,Long> {
}
