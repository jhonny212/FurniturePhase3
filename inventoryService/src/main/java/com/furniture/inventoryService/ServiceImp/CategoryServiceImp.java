package com.furniture.inventoryService.ServiceImp;

import java.util.List;

import com.furniture.inventoryService.Model.Category;
import com.furniture.inventoryService.Repository.CategoryRepository;
import com.furniture.inventoryService.Service.CategoryService;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class CategoryServiceImp implements CategoryService{

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public Category createCategory(Category category) {
        try{
            return categoryRepository.save(category);
        }catch(Exception e){
            return null;
        }
    }

    @Override
    public List<Category> getAllCategories(String name) {
        try{
            return categoryRepository.findAllByNameContainsAndStatus(name, true);
        }catch(Exception e){
            return null;
        }
    }


    
}
