package com.sumanth.FoodieGo.Controller;


import com.sumanth.FoodieGo.Dto.CartItemDto;
import com.sumanth.FoodieGo.Entity.CartItem;
import com.sumanth.FoodieGo.Mapper.CartItemMapper;
import com.sumanth.FoodieGo.Service.CartItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/cart")
public class CartItemController {

    private final CartItemService cartItemService;

    private final CartItemMapper cartItemMapper;

    public CartItemController(CartItemService cartItemService, CartItemMapper cartItemMapper) {
        this.cartItemService = cartItemService;
        this.cartItemMapper = cartItemMapper;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addCartItem(@RequestBody CartItemDto cartItemDto){
        try{
            CartItem cartItem = this.cartItemMapper.mapToModel(cartItemDto);
            cartItem = this.cartItemService.createCartItem(cartItem);
            cartItemDto = this.cartItemMapper.modelToDto(cartItem);
            return ResponseEntity.ok(cartItemDto);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("message",e.getMessage()));
        }
    }

    @GetMapping("/{cartItemId}")
    public ResponseEntity<?> getById(@PathVariable int cartItemId){
        try{
            CartItem cartItem =this.cartItemService.getByCartItemId(cartItemId);
            CartItemDto cartItemDto = this.cartItemMapper.modelToDto(cartItem);

            return ResponseEntity.ok(cartItemDto);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("message",e.getMessage()));
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateCartItem(@RequestBody CartItemDto cartItemDto){
        try{
            cartItemDto = this.cartItemMapper.modelToDto(this.cartItemService.updateCartItem(cartItemDto));
            return ResponseEntity.ok(cartItemDto);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("message",e.getMessage()));
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getByUserId(@PathVariable long userId){
        try{
            List<CartItem> cartItem = this.cartItemService.getByUserId(userId);
            List<CartItemDto> itemDtos = new ArrayList<>();
            for(CartItem item : cartItem){
                CartItemDto dto = this.cartItemMapper.modelToDto(item);
                itemDtos.add(dto);
            }
            return ResponseEntity.ok(itemDtos);
        }
        catch (RuntimeException e){
            return ResponseEntity.badRequest().body(Map.of("message",e.getMessage()));
        }
    }

    @DeleteMapping("/{cartId}")
    public ResponseEntity<?> deleteById(@PathVariable int cartId){
        try{
            return ResponseEntity.ok(this.cartItemService.deleteById(cartId));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("message",e.getMessage()));
        }
    }
}
