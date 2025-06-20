package com.sumanth.FoodieGo.Mapper;

import com.sumanth.FoodieGo.Dto.UserDto;
import com.sumanth.FoodieGo.Dto.UserProfile;
import com.sumanth.FoodieGo.Entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User mapToModel(UserDto userDto){
        User user = new User();
        user.setName(userDto.getName());
        user.setLastName(userDto.getLastName());
        user.setUserName(userDto.getUserName());
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

    public UserProfile modelToProfile(User user){
        UserProfile profile = new UserProfile();

        profile.setId(user.getId());
        profile.setName(user.getName());
        profile.setAddress(user.getAddress());
        profile.setRole(user.getRole());
        profile.setEmail(user.getEmail());
        profile.setPhoneNumber(user.getPhoneNumber());

        return profile;
    }
}
