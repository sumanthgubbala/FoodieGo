package com.sumanth.FoodieGo.Dto;

import com.sumanth.FoodieGo.enums.Status;
import lombok.Data;

import java.sql.Time;

@Data
public class RestaurantResponseDto {

    private int id;

    private String Name;

    private String address;

    private String phoneNumber;

    private String email;

    private String imgUrl;

    private long ownerId;

    private Time openingTime;

    private Time closingTime;

    private Status status;


}
