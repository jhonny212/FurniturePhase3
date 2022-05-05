package com.furniture.saleService.Service;

import com.furniture.saleService.Model.Furniture;
import com.furniture.saleService.Model.FurnitureInBill;
import com.furniture.saleService.Model.Profile;
import com.furniture.saleService.Repository.FurnitureInBillRepository;
import com.furniture.saleService.config.JWTAuthorizationFilter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FurnitureInBillServiceImpTest {

    @Mock
    private JWTAuthorizationFilter jwt;
    @Mock
    private FurnitureInBillRepository furnitureInBillRepository;
    @InjectMocks
    private FurnitureInBillServiceImp furnitureInBillServiceImp;

    @BeforeEach
    public void setUp(){ MockitoAnnotations.openMocks(this); }

    @Test
    public void getFurnituresInBillBySession(){
        Profile profile = new Profile();
        Mockito.when(
                jwt.getProfileFromToken(Mockito.anyString())
        ).thenReturn(profile);
        Mockito.when(
                furnitureInBillRepository.findByProfile(Mockito.any(Profile.class))
        ).thenReturn(new ArrayList<>());
        assertEquals(
                HttpStatus.OK,
                furnitureInBillServiceImp.getFurnituresInBillBySession("anyToken", jwt).getStatusCode()
        );
    }

    @Test
    public void removeFurnitureFromBillSuccess(){
        Profile profile = new Profile();
        FurnitureInBill furnitureInBill = new FurnitureInBill();
        Mockito.when(
                jwt.getProfileFromToken(Mockito.anyString())
        ).thenReturn(profile);
        Mockito.when(
                furnitureInBillRepository.findByFurnitureCodeAndProfile(Mockito.anyInt(), Mockito.any(Profile.class))
        ).thenReturn(furnitureInBill);
        Mockito.when(
                furnitureInBillRepository.existsById(Mockito.anyInt())
        ).thenReturn(true);
        assertEquals(
                ResponseEntity.status(HttpStatus.OK).body(false),
                furnitureInBillServiceImp.removeFurnitureFromBill("anyToken",1, jwt)
        );
    }

    @Test
    public void removeFurnitureFromBillFailed(){
        Profile profile = new Profile();
        FurnitureInBill furnitureInBill = new FurnitureInBill();
        Mockito.when(
                jwt.getProfileFromToken(Mockito.anyString())
        ).thenReturn(profile);
        Mockito.when(
                furnitureInBillRepository.findByFurnitureCodeAndProfile(Mockito.anyInt(), Mockito.any(Profile.class))
        ).thenReturn(furnitureInBill);
        Mockito.when(
                furnitureInBillRepository.existsById(Mockito.anyInt())
        ).thenReturn(false);
        assertEquals(
                ResponseEntity.status(HttpStatus.OK).body(true),
                furnitureInBillServiceImp.removeFurnitureFromBill("anyToken",1, jwt)
        );
    }

    @Test
    public void addFurnitureFromBillSuccess(){
        Profile profile = new Profile();
        Furniture furniture = new Furniture();
        FurnitureInBill furnitureInBill = new FurnitureInBill(furniture, profile);
        Mockito.when(
                jwt.getProfileFromToken(Mockito.anyString())
        ).thenReturn(profile);
        Mockito.when(
                furnitureInBillRepository.save(Mockito.any(FurnitureInBill.class))
        ).thenReturn(furnitureInBill);
        Mockito.when(
                furnitureInBillRepository.existsById(furnitureInBill.getId())
        ).thenReturn(true);
        assertEquals(
                ResponseEntity.status(HttpStatus.OK).body(true),
                furnitureInBillServiceImp.addFurnitureToBill(furniture, "anyToken", jwt)
        );
    }

    @Test
    public void addFurnitureFromBillFailed(){
        Profile profile = new Profile();
        Furniture furniture = new Furniture();
        FurnitureInBill furnitureInBill = new FurnitureInBill(furniture, profile);
        Mockito.when(
                jwt.getProfileFromToken(Mockito.anyString())
        ).thenReturn(profile);
        Mockito.when(
                furnitureInBillRepository.save(Mockito.any(FurnitureInBill.class))
        ).thenReturn(furnitureInBill);
        Mockito.when(
                furnitureInBillRepository.existsById(furnitureInBill.getId())
        ).thenReturn(false);
        assertEquals(
                ResponseEntity.status(HttpStatus.OK).body(false),
                furnitureInBillServiceImp.addFurnitureToBill(furniture, "anyToken", jwt)
        );
    }

    @Test
    public void deleteAllFurnituresInBillFromSession(){
        Profile profile = new Profile();
        Mockito.when(
                jwt.getProfileFromToken(Mockito.anyString())
        ).thenReturn(profile);
        assertEquals(
                ResponseEntity.status(HttpStatus.OK).body(true),
                furnitureInBillServiceImp.deleteAllFurnituresInBillFromSession("anyToken", jwt)
        );
    }

    @Test
    public void isFurnitureOnSessionTrue(){
        Profile profile = new Profile();
        Mockito.when(
                jwt.getProfileFromToken(Mockito.anyString())
        ).thenReturn(profile);
        Mockito.when(
                furnitureInBillRepository.existsByFurnitureCodeAndProfile(Mockito.anyInt(),Mockito.any(Profile.class))
        ).thenReturn(true);
        assertEquals(
                ResponseEntity.status(HttpStatus.OK).body(true),
                furnitureInBillServiceImp.isFurnitureOnSession(1,"anyToken",jwt)
        );
    }

    @Test
    public void isFurnitureOnSessionFalse(){
        Profile profile = new Profile();
        Mockito.when(
                jwt.getProfileFromToken(Mockito.anyString())
        ).thenReturn(profile);
        Mockito.when(
                furnitureInBillRepository.existsByFurnitureCodeAndProfile(Mockito.anyInt(),Mockito.any(Profile.class))
        ).thenReturn(false);
        assertEquals(
                ResponseEntity.status(HttpStatus.OK).body(false),
                furnitureInBillServiceImp.isFurnitureOnSession(1,"anyToken",jwt)
        );
    }
}
