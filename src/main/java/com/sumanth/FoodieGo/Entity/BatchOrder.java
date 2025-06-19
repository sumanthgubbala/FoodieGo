package com.sumanth.FoodieGo.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "batch_orders")
@Getter
@Setter
public class BatchOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "batchOrder", cascade = CascadeType.ALL)
    private List<Order> orders;

    private LocalDateTime placedAt = LocalDateTime.now();

    private double totalAmount;
}
