package com.furniture.saleService.Service;

import com.furniture.saleService.Model.BillDetails;
import com.furniture.saleService.Model.Furniture;
import com.furniture.saleService.Model.Plan;
import com.furniture.saleService.Repository.BillDetailRepository;
import com.furniture.saleService.Repository.FurnitureRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class FurnitureServiceImpTest {
    @Mock
    private BillDetailRepository billDetailRepository;
    @Mock
    private FurnitureRepository furnitureRepository;
    @InjectMocks
    private FurnitureServiceImp furnitureServiceImp;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void returnFurnitureWhenEmptyFurniture() throws ParseException{
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
        Optional<Furniture> op = Optional.empty();
        Date dd = date.parse("2022-04-10");
        Mockito.when(
                billDetailRepository.getDetail(Mockito.anyInt(),Mockito.anyInt())
        ).thenReturn(dd);
        Mockito.when(
                furnitureRepository.findById(Mockito.anyInt())
        ).thenReturn(op);
        assertFalse(furnitureServiceImp.returnFurniture(1,2));
    }

    @Test
    void returnFurnitureWhenNoCorrectDifTime() throws ParseException{
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
        Optional<Furniture> op = Optional.empty();
        Date dd = date.parse("2022-03-10");
        Mockito.when(
                billDetailRepository.getDetail(Mockito.anyInt(),Mockito.anyInt())
        ).thenReturn(dd);
        Mockito.when(
                furnitureRepository.findById(Mockito.anyInt())
        ).thenReturn(op);
        assertFalse(furnitureServiceImp.returnFurniture(1,2));
    }

    @Test
    void getDiffDay() throws ParseException{
        assertTrue(furnitureServiceImp.getDiffDay(LocalDate.now()));
        assertFalse(furnitureServiceImp.getDiffDay(LocalDate.of(2023,01,01)));
    }

    @Test
    void repaymentWithNullAndNotNullPlan() throws ParseException{
        Furniture furniture = new Furniture();
        furniture.setCost(100);
        furniture.setPrice(200);
        furniture.setCode(1);
        furniture.setPlan(new Plan());
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
        Date dd = date.parse("2022-04-11");
        Optional<BillDetails> op = Optional.of(new BillDetails());
        Mockito.when(
                furnitureRepository.save(Mockito.any(Furniture.class))
        ).thenReturn(furniture);
        Mockito.when(
                billDetailRepository.findById(Mockito.anyInt())
        ).thenReturn(op);
        Mockito.when(
                billDetailRepository.save(Mockito.any(BillDetails.class))
        ).thenReturn(op.get());
        /*
        * Do a repayment and return true
        * */
        assertTrue(furnitureServiceImp.repayment(furniture,1,dd));
        /*
         * Do a repayment with a plan null and return false
         * */
        furniture.setPlan(null);
        assertFalse(furnitureServiceImp.repayment(furniture,1,dd));

    }

    @Test
    void doRepaymentWithEmptyBillDetails()throws ParseException{
        Furniture furniture = new Furniture();
        furniture.setCost(100);
        furniture.setPrice(200);
        furniture.setCode(1);
        furniture.setPlan(new Plan());
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
        Date dd = date.parse("2022-04-11");
        Optional<BillDetails> op = Optional.empty();
        Mockito.when(
                furnitureRepository.save(Mockito.any(Furniture.class))
        ).thenReturn(furniture);
        Mockito.when(
                billDetailRepository.findById(Mockito.anyInt())
        ).thenReturn(op);
        Mockito.when(
                furnitureRepository.save(Mockito.any(Furniture.class))
        ).thenReturn(furniture);
        assertFalse(furnitureServiceImp.repayment(furniture,1,dd));
    }
}