package com.furniture.inventoryService.Controller;

import com.furniture.inventoryService.Model.Category;
import com.furniture.inventoryService.ServiceImp.CategoryServiceImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "category/")
public class CategoryController {

    @Autowired
    private CategoryServiceImp categoryServiceImp;
    
    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody Category category){
        Category cat = categoryServiceImp.createCategory(category);
        if (cat == null){
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(cat,HttpStatus.OK);
    }
}
