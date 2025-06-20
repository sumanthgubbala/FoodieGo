package com.sumanth.FoodieGo.Controller;

import com.sumanth.FoodieGo.Dto.LoginDto;
import com.sumanth.FoodieGo.Dto.UserDto;
import com.sumanth.FoodieGo.Dto.UserProfile;
import com.sumanth.FoodieGo.Entity.User;
import com.sumanth.FoodieGo.Mapper.UserMapper;
import com.sumanth.FoodieGo.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    private final UserMapper userMapper;

    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @PostMapping("/register")
    public ResponseEntity<?> addUser(@Valid @RequestBody UserDto userDto){
        User user = userMapper.mapToModel(userDto);
        return ResponseEntity.ok(this.userService.createUser(user));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getById(@PathVariable long userId){
        try{
            UserProfile profile = this.userMapper.modelToProfile(this.userService.getByUserId(userId));
            return ResponseEntity.ok(profile);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateUser(@Valid @RequestBody UserDto userDto){
        User user = this.userMapper.mapToModel(userDto);
        user.setId(userDto.getId());
        try {
            return ResponseEntity.ok(this.userService.update(user));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto){
        try{
            User user = this.userService.login(loginDto);
            UserDto userDto = this.userMapper.modelToDto(user);
            return ResponseEntity.ok(userDto);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<?> remove(@PathVariable long userId){
        try{
            return ResponseEntity.ok(this.userService.delete(userId));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of("message",e.getMessage()));
        }
    }
}
