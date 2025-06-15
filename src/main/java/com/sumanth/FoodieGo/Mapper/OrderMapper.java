package com.sumanth.FoodieGo.Mapper;

import com.sumanth.FoodieGo.Dto.OrderItemDto;
import com.sumanth.FoodieGo.Dto.OrderResponseDto;
import com.sumanth.FoodieGo.Entity.Order;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderMapper {

    public OrderResponseDto modelToDto(Order order){
        OrderResponseDto response = new OrderResponseDto();

        response.setOrderId(order.getId());
        response.setUserId(order.getUser().getId());
        response.setRestaurantId(order.getRestaurant().getId());
        response.setDeliveryAddress(order.getDeliveryAddress());
        response.setOrderStatus(order.getOrderStatus());
        response.setPlacedAt(order.getPlacedAt());
        response.setTotalAmount(order.getTotalAmount());

        List<OrderItemDto> itemDtos = order.getOrderItems().stream().map(
                item ->{
                    OrderItemDto dto = new OrderItemDto();
                    dto.setMenuItemId(item.getMenuItem().getId());
                    dto.setQuantity(item.getQuantity());
                    dto.setPrice(item.getPrice());
                    return dto;
                }
        ).collect(Collectors.toList());
        response.setOrderItems(itemDtos);

        return response;

    }
}
