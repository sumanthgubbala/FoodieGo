package com.sumanth.FoodieGo.Entity;

import com.sumanth.FoodieGo.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Setter
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    private User user;

    @ManyToOne
    private BatchOrder batchOrder;

    @ManyToOne
    @JoinColumn(name = "restaurant_id",nullable = false)
    private Restaurant restaurant;

    @Column(name = "total_amount",nullable = false)
    private double totalAmount;

    @Enumerated(EnumType.STRING)
    @Column(name = "order_status",nullable = false)
    private OrderStatus orderStatus = OrderStatus.PLACED;

    @Column(name = "delivery_address",nullable = false)
    private String deliveryAddress;

    @Column(name = "placed_at",nullable = false)
    private LocalDateTime placedAt;

    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL)
    private List<OrderItem> orderItems;
}
