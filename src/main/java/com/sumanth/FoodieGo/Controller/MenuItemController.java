package com.sumanth.FoodieGo.Controller;

import com.sumanth.FoodieGo.Dto.MenuItemDto;
import com.sumanth.FoodieGo.Dto.MenuItemsResponseDto;
import com.sumanth.FoodieGo.Entity.MenuItem;
import com.sumanth.FoodieGo.Mapper.MenuItemMapper;
import com.sumanth.FoodieGo.Mapper.MenuItemsResponse;
import com.sumanth.FoodieGo.Service.MenuItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/menuItems")
public class MenuItemController {

    private final MenuItemService menuItemService;

    private final MenuItemMapper menuItemMapper;

    private final MenuItemsResponse menuItemsResponse;

    public MenuItemController(MenuItemService menuItemService, MenuItemMapper menuItemMapper, MenuItemsResponse menuItemsResponse) {
        this.menuItemService = menuItemService;
        this.menuItemMapper = menuItemMapper;
        this.menuItemsResponse = menuItemsResponse;
    }

    @PostMapping("/add")
    public ResponseEntity<?> create(@RequestBody MenuItemDto menuItemDto){
        try{
            MenuItem menuItem = this.menuItemMapper.mapToModel(menuItemDto);
            return ResponseEntity.ok(this.menuItemService.createMenuItem(menuItem));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("message",e.getMessage()));
        }
    }

    @GetMapping("/{menuItemId}")
    public ResponseEntity<?> getById(@PathVariable int menuItemId){
        try{
            return ResponseEntity.ok(this.menuItemService.getByMenuItemId(menuItemId));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("message",e.getMessage()));
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAll(){
        List<MenuItemsResponseDto> responseDtos = new ArrayList<>();
        List<MenuItem> items = this.menuItemService.getAllMenuItems();
        for (MenuItem item : items){
            MenuItemsResponseDto dto = this.menuItemsResponse.convertToMenuItemsResponse(item);
            responseDtos.add(dto);
        }
        return ResponseEntity.ok(responseDtos);
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<?> getByCategory(@PathVariable int categoryId){
        try{
            List<MenuItem> menuItems = this.menuItemService.getByCategoryId(categoryId);
            List<MenuItemsResponseDto> responses = new ArrayList<>();
            for(MenuItem item : menuItems){
                MenuItemsResponseDto dto = this.menuItemsResponse.convertToMenuItemsResponse(item);
                responses.add(dto);
            }
            return ResponseEntity.ok(responses);
        }catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("message",e.getMessage()));
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody MenuItemDto menuItemDto){
        try{
            MenuItem menuItem = this.menuItemMapper.mapToModel(menuItemDto);
            menuItem.setId(menuItemDto.getId());
            return ResponseEntity.ok(this.menuItemService.updateMenuItem(menuItem));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("message",e.getMessage()));
        }
    }

    @PutMapping("/image")
    public ResponseEntity<?> updateImg(@RequestBody MenuItem menuItem){
        try{
            return ResponseEntity.ok(this.menuItemService.updateImage(menuItem));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("message",e.getMessage()));
        }
    }



    @DeleteMapping("/{menuItemId}")
    public ResponseEntity<?> delete(@PathVariable int menuItemId){
        try{
            return ResponseEntity.ok(this.menuItemService.deleteMenuItem(menuItemId));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("message",e.getMessage()));
        }
    }

    @GetMapping("/restaurant/{restaurantId}")
    public ResponseEntity<?> getByRestaurant(@PathVariable int restaurantId){
        try{
            List<MenuItem> items = this.menuItemService.getByRestaurantId(restaurantId);
            List<MenuItemsResponseDto> responseDtos = new ArrayList<>();
            for(MenuItem item : items){
                MenuItemsResponseDto dto = this.menuItemsResponse.convertToMenuItemsResponse(item);
                responseDtos.add(dto);
            }
            return ResponseEntity.ok(responseDtos);
        }catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("message",e.getMessage()));
        }
    }
}
