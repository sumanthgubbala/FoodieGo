package com.sumanth.FoodieGo.Service;

import com.sumanth.FoodieGo.Dto.OrderItemDto;
import com.sumanth.FoodieGo.Dto.OrderRequestDto;
import com.sumanth.FoodieGo.Entity.*;
import com.sumanth.FoodieGo.Repository.OrderRepository;
import com.sumanth.FoodieGo.enums.OrderStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    private  final UserService userService;

    private  final MenuItemService menuItemService;

    private  final  RestaurantService restaurantService;

    public Order placeOrder(OrderRequestDto orderRequestDto){

        User user = this.userService.getByUserId(orderRequestDto.getUserId());

        Restaurant restaurant = this.restaurantService.getByRestaurantId(orderRequestDto.getRestaurantId());

        Order order = new Order();

        order.setUser(user);
        order.setRestaurant(restaurant);
        order.setDeliveryAddress(orderRequestDto.getDeliveryAddress());
        order.setOrderStatus(OrderStatus.PLACED);
        order.setPlacedAt(LocalDateTime.now());

        List<OrderItem> orderItems = new ArrayList<>();
        double total = 0.0;

        for(OrderItemDto itemDto : orderRequestDto.getOrderItems()){
            MenuItem menuItem = this.menuItemService.getByMenuItemId(itemDto.getMenuItemId());

            OrderItem orderItem = new OrderItem();

            orderItem.setOrder(order);
            orderItem.setMenuItem(menuItem);
            orderItem.setQuantity(itemDto.getQuantity());
            orderItem.setPrice(menuItem.getPrice());

            total += itemDto.getQuantity() * menuItem.getPrice();
            orderItems.add(orderItem);
        }

        order.setOrderItems(orderItems);
        order.setTotalAmount(total);

        return this.orderRepository.save(order);
    }

    public Order getByOrderId(int orderId){
        return this.orderRepository.findById(orderId)
                .orElseThrow(
                        ()-> new RuntimeException("Order not found")
                );
    }
}
