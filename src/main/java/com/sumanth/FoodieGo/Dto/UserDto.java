package com.sumanth.FoodieGo.Dto;

import com.sumanth.FoodieGo.enums.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserDto {

    @NotNull
    private long id;

    @NotBlank(message = "Name cannot be null or empty")
    private String name;

    private String lastName;

    private String userName;



    @NotNull(message = "Email cannot be null")
    @Email(message = "Email should be valid")
    private String email;

    @NotBlank(message = "Password cannot be null")
    private String password;

    @NotBlank(message = "Phone cannot be blank")
    private String phoneNumber;

    @NotBlank(message = "Address cannot be blank")
    private String address;


    private Role role = Role.CUSTOMER;
}
