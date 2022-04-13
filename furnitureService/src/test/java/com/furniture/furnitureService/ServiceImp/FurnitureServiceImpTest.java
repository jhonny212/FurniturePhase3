package com.furniture.furnitureService.ServiceImp;

import com.furniture.furnitureService.Model.Furniture;
import com.furniture.furnitureService.Repository.FurnitureRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class FurnitureServiceImpTest {
    @Mock
    private FurnitureRepository furnitureRepository;
    @InjectMocks
    private FurnitureServiceImp furnitureServiceImp;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void putOnSale() {
        Furniture tmp = new Furniture();
        Optional<Furniture> opt = Optional.of(tmp);
        Mockito.when(
                furnitureRepository.findById(Mockito.anyInt())
        ).thenReturn(opt);
        Mockito.when(
                furnitureRepository.save(tmp)
        ).thenReturn(tmp);
        assertTrue(furnitureServiceImp.putOnSale(1));
    }

    @Test
    void putOnSaleAndReturnFalse(){
        Optional<Furniture> tmp = Optional.empty();
        Mockito.when(
                furnitureRepository.findById(Mockito.anyInt())
        ).thenReturn(tmp);
        assertFalse(furnitureServiceImp.putOnSale(1));
    }
}