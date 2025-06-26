package com.sumanth.FoodieGo.Service;

import com.sumanth.FoodieGo.Dto.LoginDto;
import com.sumanth.FoodieGo.Dto.UserProfile;
import com.sumanth.FoodieGo.Entity.User;
import com.sumanth.FoodieGo.Repository.UserRepository;
import com.sumanth.FoodieGo.Security.UserPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

@Service
public class UserService implements UserDetailsService {

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

    public User update(UserProfile user){
        User existing = this.getByUserId(user.getId());
        existing.setEmail(user.getEmail());
        existing.setAddress(user.getAddress());
        existing.setPhoneNumber(user.getPhoneNumber());
        existing.setRole(user.getRole());

        return this.userRepository.save(existing);
    }

    public User getByUserName(String userName){
        User user = this.userRepository.findByUserName(userName).orElse(null);
        return user;
    }


    public User login(LoginDto loginDto){
        User user = this.userRepository.findByUserName(loginDto.getUserName()).orElseThrow(
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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(username).orElse(null);

        if(user == null){
            System.out.println("User Not found");
            throw new RuntimeException("User Not Found");
        }

        return new UserPrincipal(user);
    }
}
