package com.sumanth.FoodieGo.Service;

import com.sumanth.FoodieGo.Dto.PlaceBatchOrderDto;
import com.sumanth.FoodieGo.Entity.*;
import com.sumanth.FoodieGo.Repository.BatchOrderRepository;
import com.sumanth.FoodieGo.Repository.CartItemRepository;
import com.sumanth.FoodieGo.enums.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class BatchOrderService {

    @Autowired
    private UserService userService;

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private MenuItemService menuItemService;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private BatchOrderRepository batchOrderRepository;

    public BatchOrder placeOrder(PlaceBatchOrderDto dto){
        User user = this.userService.getByUserId(dto.getUserId());

        List<CartItem> cartItems = this.cartItemRepository.findByUser_Id(user.getId());

        Map<Restaurant,List<CartItem>> itemsByRestaurant = cartItems.stream()
                .collect(Collectors.groupingBy(item -> item.getMenuItem().getRestaurant()));

        BatchOrder batchOrder = new BatchOrder();
        batchOrder.setUser(user);
        batchOrder.setPlacedAt(LocalDateTime.now());
        batchOrder.setOrders(new ArrayList<>());
        batchOrder = batchOrderRepository.save(batchOrder);

        double batchTotal = 0.0;

        for(Map.Entry<Restaurant,List<CartItem>> entry : itemsByRestaurant.entrySet()){
            Restaurant restaurant = entry.getKey();
            List<CartItem> items = entry.getValue();

            Order order = new Order();
            order.setUser(user);
            order.setRestaurant(restaurant);
            order.setBatchOrder(batchOrder);
            order.setOrderStatus(OrderStatus.PLACED);
            order.setOrderItems(new ArrayList<>());
            order.setPlacedAt(LocalDateTime.now());
            order.setDeliveryAddress(user.getAddress());

            double orderTotal = 0.0;

            for(CartItem ci : items){
                OrderItem item = new OrderItem();
                item.setMenuItem(ci.getMenuItem());
                item.setQuantity(ci.getQuantity());
                item.setOrder(order);
                item.setPrice(ci.getMenuItem().getPrice());
                order.getOrderItems().add(item);

                orderTotal += ci.getMenuItem().getPrice() * ci.getQuantity();

            }

            order.setTotalAmount(orderTotal);
            batchTotal +=orderTotal;
            batchOrder.getOrders().add(order);
        }
        batchOrder.setTotalAmount(batchTotal);

        batchOrderRepository.save(batchOrder);

        cartItemRepository.deleteAll(cartItems);

        return batchOrder;
    }

    public List<BatchOrder> getAllOrdersByUserId(long userId){
        return this.batchOrderRepository.findByUser_Id(userId);
    }
}
