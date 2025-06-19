package com.sumanth.FoodieGo.Dto;

import com.sumanth.FoodieGo.enums.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserProfile {

    private long id;

    private String name;

    private String email;

    private String phoneNumber;

    private String address;

    private Role role = Role.CUSTOMER;
}
