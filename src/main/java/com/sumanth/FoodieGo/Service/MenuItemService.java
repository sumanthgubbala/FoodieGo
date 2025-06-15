package com.sumanth.FoodieGo.Service;

import com.sumanth.FoodieGo.Entity.MenuItem;
import com.sumanth.FoodieGo.Repository.MenuItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuItemService {

    private final MenuItemRepository menuItemRepository;

    public MenuItemService(MenuItemRepository menuItemRepository) {
        this.menuItemRepository = menuItemRepository;
    }

    public MenuItem createMenuItem(MenuItem menuItem){
        return this.menuItemRepository.save(menuItem);
    }

    public MenuItem getByMenuItemId(int menuItemId){
        return this.menuItemRepository.findById(menuItemId).orElseThrow(
                () -> new RuntimeException("MenuItem Id not Found")
        );
    }

    public List<MenuItem> getAllMenuItems(){
        return this.menuItemRepository.findAll();
    }

    public MenuItem updateMenuItem(MenuItem menuItem){
        MenuItem existing = this.getByMenuItemId(menuItem.getId());
        existing.setName(menuItem.getName());
        existing.setPrice(menuItem.getPrice());
        existing.setAvailable(menuItem.isAvailable());
        existing.setDescription(menuItem.getDescription());
        existing.setCategory(menuItem.getCategory());
        existing.setRestaurant(menuItem.getRestaurant());
        existing.setImgUrl(menuItem.getImgUrl());
        return this.menuItemRepository.save(existing);
    }

    public String deleteMenuItem(int menuItemId){
        MenuItem menuItem = this.getByMenuItemId(menuItemId);
        this.menuItemRepository.delete(menuItem);
        return "Deleted Successfully";
    }
}
