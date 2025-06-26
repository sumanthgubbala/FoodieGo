package com.sumanth.FoodieGo.Service;

import com.sumanth.FoodieGo.Dto.CartItemDto;
import com.sumanth.FoodieGo.Entity.CartItem;
import com.sumanth.FoodieGo.Repository.CartItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartItemService {

    private final CartItemRepository cartItemRepository;

    public CartItemService(CartItemRepository cartItemRepository) {
        this.cartItemRepository = cartItemRepository;
    }

    public CartItem createCartItem(CartItem cartItem){
        return this.cartItemRepository.save(cartItem);
    }

    public CartItem getByCartItemId(int cartItemId){
        return this.cartItemRepository.findById(cartItemId).orElseThrow(
                ()-> new RuntimeException("CartItem Id is Not Found")
        );
    }

    public List<CartItem> getAllCartItems(){
        return this.cartItemRepository.findAll();
    }

    public List<CartItem> getByUserId(long userId){
        return this.cartItemRepository.findByUser_Id(userId);
    }

    public CartItem updateCartItem(CartItemDto cartItem){
        CartItem existing = this.getByCartItemId(cartItem.getId());
        existing.setQuantity(cartItem.getQuantity());
        return this.cartItemRepository.save(existing);
    }

    public String deleteById(int cartItemId){
        CartItem cartItem = this.getByCartItemId(cartItemId);
        this.cartItemRepository.delete(cartItem);
        return "Deleted Successfully";
    }
}
