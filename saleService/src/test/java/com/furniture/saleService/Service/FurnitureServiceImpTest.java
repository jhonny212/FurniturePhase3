package com.furniture.saleService.Service;

import com.furniture.saleService.Model.*;
import com.furniture.saleService.Repository.BillDetailRepository;
import com.furniture.saleService.Repository.FurnitureRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

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

    @Test
    public void putFurnitureOnSaleSuccesfully(){
        Optional<Furniture> furniture = Optional.of(new Furniture());
        Mockito.when(
                furnitureRepository.findById(Mockito.anyInt())
        ).thenReturn(furniture);
        Mockito.when(
                furnitureRepository.save(furniture.get())
        ).thenReturn(furniture.get());
        assertEquals(
                HttpStatus.OK,
                furnitureServiceImp.putFurnitureOnSale(new OnSaleData(1,0.0)).getStatusCode()
        );
    }

    @Test
    public void putFurnitureOnSaleFailed(){
        Optional<Furniture> furniture = Optional.of(new Furniture());
        Mockito.when(
                furnitureRepository.findById(Mockito.anyInt())
        ).thenReturn(Optional.empty());
        assertEquals(
                HttpStatus.NOT_FOUND,
                furnitureServiceImp.putFurnitureOnSale(new OnSaleData(1,0.0)).getStatusCode()
        );
    }

    @Test
    public void getFurnituresByState() {
        Page<Furniture> pagina = new PageImpl<Furniture>(new ArrayList());
        Mockito.when(
                furnitureRepository.findByStatusAndNameContainsIgnoreCase(Mockito.anyInt(),Mockito.anyString(),Mockito.any(Pageable.class))
        ).thenReturn(pagina);
        assertEquals(
                HttpStatus.OK,
                furnitureServiceImp.getFurnituresByStatus(
                        Optional.of(1),
                        Optional.of("anyValue"),
                        1
                ).getStatusCode()
        );
    }

    @Test
    public void verifyOnSaleAvailableSuccess(){
        Optional<Furniture> furniture = Optional.of(new Furniture(1,"",0.0,0.0,new Date(),"","",null,null,1));

        ArrayList<BillDetails> mockedDetails = new ArrayList();
        mockedDetails.add(new BillDetails(1, new Bill(), furniture.get(), 0.0));
        mockedDetails.add(new BillDetails(2, new Bill(), furniture.get(), 0.0));
        mockedDetails.add(new BillDetails(3, new Bill(), furniture.get(), 0.0));

        Mockito.when(
                furnitureRepository.findById(Mockito.anyInt())
        ).thenReturn(furniture);
        assertEquals(
                ResponseEntity.status(HttpStatus.OK).body(true),
                furnitureServiceImp.verifyOnSale(mockedDetails)
        );
    }

    @Test
    public void verifyOnSaleNotAvailableSuccess(){
        Optional<Furniture> furniture = Optional.of(new Furniture(1,"",0.0,0.0,new Date(),"","",null,null,0));

        ArrayList<BillDetails> mockedDetails = new ArrayList();
        mockedDetails.add(new BillDetails(1, new Bill(), furniture.get(), 0.0));
        mockedDetails.add(new BillDetails(2, new Bill(), furniture.get(), 0.0));
        mockedDetails.add(new BillDetails(3, new Bill(), furniture.get(), 0.0));

        Mockito.when(
                furnitureRepository.findById(Mockito.anyInt())
        ).thenReturn(furniture);
        assertEquals(
                ResponseEntity.status(HttpStatus.OK).body(false),
                furnitureServiceImp.verifyOnSale(mockedDetails)
        );
    }

    @Test
    public void verifyOnSaleFailed(){
        Optional<Furniture> furniture = Optional.of(new Furniture(1,"",0.0,0.0,new Date(),"","",null,null,0));

        ArrayList<BillDetails> mockedDetails = new ArrayList();
        mockedDetails.add(new BillDetails(1, new Bill(), furniture.get(), 0.0));
        mockedDetails.add(new BillDetails(2, new Bill(), furniture.get(), 0.0));
        mockedDetails.add(new BillDetails(3, new Bill(), furniture.get(), 0.0));

        Mockito.when(
                furnitureRepository.findById(Mockito.anyInt())
        ).thenThrow(RuntimeException.class);
        assertEquals(
                HttpStatus.INTERNAL_SERVER_ERROR,
                furnitureServiceImp.verifyOnSale(mockedDetails).getStatusCode()
        );
    }

    @Test
    public void putSoldSuccesfull(){
        Optional<Furniture> furniture = Optional.of(new Furniture(1,"",0.0,0.0,new Date(),"","",null,null,0));

        ArrayList<BillDetails> mockedDetails = new ArrayList();
        mockedDetails.add(new BillDetails(1, null, furniture.get(), 0.0));
        mockedDetails.add(new BillDetails(2, null, furniture.get(), 0.0));
        mockedDetails.add(new BillDetails(3, null, furniture.get(), 0.0));
        BillData billData = new BillData(new Bill(), mockedDetails);

        Mockito.when(
                furnitureRepository.findById(Mockito.anyInt())
        ).thenReturn(furniture);
        Mockito.when(
                furnitureRepository.save(Mockito.any(Furniture.class))
        ).thenReturn(furniture.get());
        Mockito.when(
                billDetailRepository.save(Mockito.any(BillDetails.class))
        ).thenReturn(new BillDetails());

        assertEquals(
                HttpStatus.OK,
                furnitureServiceImp.putSold(billData).getStatusCode()
        );
    }

    @Test
    public void putSoldFailed(){
        Optional<Furniture> furniture = Optional.of(new Furniture(1,"",0.0,0.0,new Date(),"","",null,null,0));

        ArrayList<BillDetails> mockedDetails = new ArrayList();
        mockedDetails.add(new BillDetails(1, null, furniture.get(), 0.0));
        mockedDetails.add(new BillDetails(2, null, furniture.get(), 0.0));
        mockedDetails.add(new BillDetails(3, null, furniture.get(), 0.0));
        BillData billData = new BillData(new Bill(), mockedDetails);

        Mockito.when(
                furnitureRepository.findById(Mockito.anyInt())
        ).thenThrow(RuntimeException.class);
        assertEquals(
                HttpStatus.INTERNAL_SERVER_ERROR,
                furnitureServiceImp.putSold(billData).getStatusCode()
        );
    }
}