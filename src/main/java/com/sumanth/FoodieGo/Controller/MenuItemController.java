package com.sumanth.FoodieGo.Controller;

import com.sumanth.FoodieGo.Dto.MenuItemDto;
import com.sumanth.FoodieGo.Entity.MenuItem;
import com.sumanth.FoodieGo.Mapper.MenuItemMapper;
import com.sumanth.FoodieGo.Service.MenuItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/menuItems")
public class MenuItemController {

    private final MenuItemService menuItemService;

    private final MenuItemMapper menuItemMapper;

    public MenuItemController(MenuItemService menuItemService, MenuItemMapper menuItemMapper) {
        this.menuItemService = menuItemService;
        this.menuItemMapper = menuItemMapper;
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
        return ResponseEntity.ok(this.menuItemService.getAllMenuItems());
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

    @DeleteMapping("/{menuItemId}")
    public ResponseEntity<?> delete(@PathVariable int menuItemId){
        try{
            return ResponseEntity.ok(this.menuItemService.deleteMenuItem(menuItemId));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("message",e.getMessage()));
        }
    }
}
