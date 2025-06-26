package com.sumanth.FoodieGo.Dto;

import com.sumanth.FoodieGo.enums.OrderStatus;
import lombok.Data;

import java.util.List;

@Data
public class BatchOrderResponseDto {
    private long batchOrderId;
    private double totalAmount;
    private List<RestaurantOrderDto> orders;

    private OrderStatus orderStatus;
}
