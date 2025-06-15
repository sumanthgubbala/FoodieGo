package com.sumanth.FoodieGo.Entity;


import com.sumanth.FoodieGo.enums.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;



@Entity
@Getter
@Setter
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name",nullable = false)
    private String name;

    @Column(name = "email",nullable = false,unique = true)
    private String email;

    @Column(name = "password",nullable = false)
    private String password;

    @Column(name = "phn_num",nullable = false,unique = true)
    private String phoneNumber;

    @Column(name = "address",nullable = false)
    private String address;

    @Enumerated(EnumType.STRING)
    @Column(name = "role",nullable = false)
    private Role role = Role.CUSTOMER;

}
