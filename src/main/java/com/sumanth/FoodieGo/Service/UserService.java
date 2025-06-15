package com.sumanth.FoodieGo.Service;

import com.sumanth.FoodieGo.Dto.LoginDto;
import com.sumanth.FoodieGo.Entity.User;
import com.sumanth.FoodieGo.Repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User user){
        return this.userRepository.save(user);
    }

    public User getByUserId(long userId){
        return this.userRepository.findById(userId).orElseThrow(
                () -> new RuntimeException("User Not Found")
        );
    }

    public User update(User user){
        User existing = this.getByUserId(user.getId());
        existing.setEmail(user.getEmail());
        existing.setName(user.getName());
        existing.setAddress(user.getAddress());
        existing.setPassword(user.getPassword());
        existing.setPhoneNumber(user.getPhoneNumber());
        existing.setRole(user.getRole());

        return this.userRepository.save(existing);
    }


    public User login(LoginDto loginDto){
        User user = this.userRepository.findByName(loginDto.getName()).orElseThrow(
                () -> new RuntimeException("User not Found")
        );
        if(!user.getPassword().equals(loginDto.getPassword())){
            throw new RuntimeException("password is incorrect");
        }
        return user;
    }

    public String delete(long userId){
            User user = this.getByUserId(userId);
            this.userRepository.delete(user);
            return "User Delete Successfully";
    }
}
