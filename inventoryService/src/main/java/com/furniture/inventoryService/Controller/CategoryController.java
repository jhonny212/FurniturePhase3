package com.furniture.inventoryService.Controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;

import com.furniture.inventoryService.Model.Category;
import com.furniture.inventoryService.ServiceImp.CategoryServiceImp;

import com.furniture.inventoryService.Util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController()
@RequestMapping("fabricate/category")
public class CategoryController {

    @Autowired
    private CategoryServiceImp categoryServiceImp;
    @Autowired
    private Util util;

    @PostMapping
    public ResponseEntity<Category> createCategory(@Valid @RequestBody Category category, BindingResult result){
        if(result.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,util.formatMessage(result));
        }
        Category cat = categoryServiceImp.createCategory(category);
        if (cat == null){
            category.msj = "Ya existe una categoria con el mismo nombre";
            return new ResponseEntity<>(category,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(cat,HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Category>> getAllCategories(@RequestParam Optional<String> name){
        List<Category> cat = this.categoryServiceImp.getAllCategories(name.orElse(""));
        if (cat == null){
            return new ResponseEntity<>(null,HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(cat,HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable("id") Integer id){
        return this.categoryServiceImp.deleteCategory(id);
    }
}
