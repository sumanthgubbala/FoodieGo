package com.sumanth.FoodieGo.Service;

import com.sumanth.FoodieGo.Entity.Category;
import com.sumanth.FoodieGo.Repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category createCategory(Category category){
        return this.categoryRepository.save(category);
    }

    public Category getByCategoryId(int categoryId){
        return this.categoryRepository.findById(categoryId).orElseThrow(
                () -> new RuntimeException("Category  Id is not found")
        );
    }

    public List<Category> getAll(){
        return this.categoryRepository.findAll();
    }

    public Category update(Category category){
        Category existing = getByCategoryId(category.getId());
        existing.setName(category.getName());
        return categoryRepository.save(existing);
    }

    public String delete(int categoryId){
        Category category = getByCategoryId(categoryId);
        this.categoryRepository.delete(category);
        return "Deleted Successfully";
    }
}
