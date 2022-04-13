package com.furniture.inventoryService.ServiceImp;

import com.furniture.inventoryService.Model.Category;
import com.furniture.inventoryService.Repository.CategoryRepository;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.exception.DataException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CategoryServiceImpTest {
    @Mock
    private CategoryRepository categoryRepository;
    @InjectMocks
    private CategoryServiceImp categoryServiceImp;
    Category category;
    List<Category> list;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        category = new Category("categoria1");
    }

    @Test
    void createCategoryAndReturnACategory() {
        Mockito.when(
            categoryRepository.save(Mockito.any(Category.class))
        ).thenReturn(category);
        assertEquals(categoryServiceImp.createCategory(category),category);
    }

    @Test
    void createCategoryAndReturnNull(){
        Mockito.when(
                categoryRepository.save(Mockito.any(Category.class))
        ).thenThrow(ConstraintViolationException.class);
        assertNull(categoryServiceImp.createCategory(category));
    }

    @Test
    void getAllCategories() {
        list=new ArrayList<>();
        list.add(category);
        Mockito.when(
          categoryRepository.findAllByNameContainsAndStatus(
                  Mockito.anyString(),Mockito.anyBoolean()
          )
        ).thenReturn(list);
        assertEquals(categoryServiceImp.getAllCategories(""),list);
    }

    @Test
    void getAllCategoriesAndReturnNull(){
        Mockito.when(
                categoryRepository.findAllByNameContainsAndStatus(
                        Mockito.anyString(),Mockito.anyBoolean()
                )
        ).thenThrow(DataException.class);
        assertNull(categoryServiceImp.getAllCategories(""));
    }
}