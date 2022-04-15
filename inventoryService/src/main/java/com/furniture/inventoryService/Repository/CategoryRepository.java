package com.furniture.inventoryService.Repository;

import com.furniture.inventoryService.Model.Category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category,Integer> {
    List<Category> findAllByNameContainsAndStatus(String name,boolean status);
}
