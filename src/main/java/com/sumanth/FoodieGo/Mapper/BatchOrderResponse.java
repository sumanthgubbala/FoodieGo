package com.sumanth.FoodieGo.Mapper;

import com.sumanth.FoodieGo.Dto.BatchOrderResponseDto;
import com.sumanth.FoodieGo.Dto.OrderItemDto;
import com.sumanth.FoodieGo.Dto.RestaurantOrderDto;
import com.sumanth.FoodieGo.Entity.BatchOrder;
import com.sumanth.FoodieGo.Entity.MenuItem;
import com.sumanth.FoodieGo.Entity.Order;
import com.sumanth.FoodieGo.Entity.OrderItem;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BatchOrderResponse {

    public BatchOrderResponseDto convertToBatchOrderResponseDto(BatchOrder batchOrder){
        BatchOrderResponseDto response = new BatchOrderResponseDto();
        response.setBatchOrderId(batchOrder.getId());
        response.setTotalAmount(batchOrder.getTotalAmount());

        List<RestaurantOrderDto> restaurantOrders = new ArrayList<>();

        for(Order order : batchOrder.getOrders()){
            RestaurantOrderDto restaurantOrderDto = new RestaurantOrderDto();
            restaurantOrderDto.setRestaurantId(order.getRestaurant().getId());
            restaurantOrderDto.setOrderTotal(order.getTotalAmount());

            List<OrderItemDto> itemDtos = new ArrayList<>();
            for(OrderItem item: order.getOrderItems()){
                MenuItem menuItem = item.getMenuItem();

                OrderItemDto itemDto = new OrderItemDto();
                itemDto.setMenuItemId(menuItem.getId());
                itemDto.setName(menuItem.getName());
                itemDto.setPrice(menuItem.getPrice());
                itemDto.setQuantity(item.getQuantity());

                itemDtos.add(itemDto);
            }
            restaurantOrderDto.setItems(itemDtos);
            restaurantOrders.add(restaurantOrderDto);
        }
        response.setOrders(restaurantOrders);

        return response;
    }
}
