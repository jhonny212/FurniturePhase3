package com.furniture.inventoryService.Service;

import java.util.List;

import com.furniture.inventoryService.Model.Category;

public interface CategoryService {
    
    public Category createCategory(Category category);
    public List<Category> getAllCategories(String name);

}
