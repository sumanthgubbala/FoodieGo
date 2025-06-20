package com.sumanth.FoodieGo.Controller;

import com.sumanth.FoodieGo.Entity.Category;
import com.sumanth.FoodieGo.Service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> create(@RequestBody Category category){
        return ResponseEntity.ok(this.categoryService.createCategory(category));
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<?> getById(@PathVariable int categoryId){
        try{
            return ResponseEntity.ok(this.categoryService.getByCategoryId(categoryId));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("message",e.getMessage()));
        }
    }

    @GetMapping("/home")
    public ResponseEntity<?> allCategories(){
        return ResponseEntity.ok(this.categoryService.getAll());
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody Category category){
        try{
            return ResponseEntity.ok(this.categoryService.update(category));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("message",e.getMessage()));
        }
    }

    @PutMapping("/image")
    public ResponseEntity<?> addImg(@RequestBody Category category){
        try{
            return ResponseEntity.ok(this.categoryService.addImage(category));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("message",e.getMessage()));
        }
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<?> deleteById(@PathVariable int categoryId){
        try{
            return ResponseEntity.ok(this.categoryService.delete(categoryId));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("message",e.getMessage()));
        }
    }
}
