package com.sumanth.FoodieGo.Dto;

import lombok.Data;

import java.util.List;

@Data
public class BatchOrderResponseDto {
    private long batchOrderId;
    private double totalAmount;
    private List<RestaurantOrderDto> orders;
}
