package com.sumanth.FoodieGo.Entity;

import com.sumanth.FoodieGo.enums.Status;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Time;

@Entity
@Table(name = "restaurants")
@Getter
@Setter
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "restaurant_id")
    private int id;

    @Column(name = "name",nullable = false)
    private String name;

    @Column(name = "address",nullable = false)
    private String address;

    @Column(name = "phone_number",nullable = false)
    private String phoneNumber;

    @Column(name = "email",nullable = false)
    private String email;

    @Column(name = "opening_time",nullable = false)
    private Time openingTime;

    @Column(name = "closing_time",nullable = false)
    private Time closingTime;

    @Column(name = "status",nullable = false)
    private Status status = Status.OPEN;

}
