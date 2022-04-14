package com.furniture.inventoryService.ServiceImp;

import java.util.List;

import com.furniture.inventoryService.Model.Category;
import com.furniture.inventoryService.Repository.CategoryRepository;
import com.furniture.inventoryService.Service.CategoryService;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @Override
    public ResponseEntity<String> deleteCategory(Integer id) {
        try {
            this.categoryRepository.deleteById(id);
            if (this.categoryRepository.existsById(id)) {
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("No se ha eliminado la categoría correctamente");
            } else {
                return ResponseEntity.status(HttpStatus.OK).body("Se ha eliminado la categoria de la pieza correctamente");
            }
        }catch(Exception ex){
            return ResponseEntity.status(HttpStatus.FAILED_DEPENDENCY).body(ex.getMessage());
        }
    }


}
