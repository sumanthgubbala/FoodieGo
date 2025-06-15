package com.sumanth.FoodieGo.Mapper;

import com.sumanth.FoodieGo.Dto.UserDto;
import com.sumanth.FoodieGo.Entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User mapToModel(UserDto userDto){
        User user = new User();
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setAddress(userDto.getAddress());
        user.setPassword(userDto.getPassword());
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setRole(userDto.getRole());
        return user;
    }

    public UserDto modelToDto(User user){
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setAddress(user.getAddress());
        userDto.setEmail(user.getEmail());
        userDto.setPhoneNumber(user.getPhoneNumber());
        userDto.setRole(user.getRole());
        return userDto;
    }
}
