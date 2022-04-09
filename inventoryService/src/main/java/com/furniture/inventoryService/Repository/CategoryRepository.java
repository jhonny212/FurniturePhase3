package com.furniture.inventoryService.Repository;

import com.furniture.inventoryService.Model.Category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface CategoryRepository extends JpaRepository<Category,Integer> {
    
}
