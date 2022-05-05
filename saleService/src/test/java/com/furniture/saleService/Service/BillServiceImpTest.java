package com.furniture.saleService.Service;

import com.furniture.saleService.Model.*;
import com.furniture.saleService.Repository.BillDetailRepository;
import com.furniture.saleService.Repository.BillRepository;
import com.furniture.saleService.config.JWTAuthorizationFilter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import org.hibernate.exception.DataException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

class BillServiceImpTest {
    @Mock
    private BillDetailRepository billDetailRepository;
    @Mock
    private ClientServiceImp clientServiceImp;
    @Mock
    private FurnitureServiceImp furnitureServiceImp;
    @Mock
    private JWTAuthorizationFilter jwt;
    @Mock
    private BillRepository billRepository;
    @InjectMocks
    BillServiceImp billServiceImp;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getDetailBill() {
        List<Object[]> obj = new ArrayList<>();
        Mockito.when(
                billDetailRepository.findAllDetailBils(Mockito.anyInt())
        ).thenReturn(obj);
        assertEquals(billServiceImp.getDetailBill(1),obj);
    }

    @Test
    void getDetailBillAndThrownError(){
        Mockito.when(
                billDetailRepository.findAllDetailBils(Mockito.anyInt())
        ).thenThrow(DataException.class);
        assertNull(billServiceImp.getDetailBill(1));
    }

    @Test
    void getClientByIdBill() {
        Object obj = new Object();
        Mockito.when(
          billDetailRepository.findClient(Mockito.anyInt())
        ).thenReturn(obj);
        assertEquals(billServiceImp.getClientByIdBill(1),obj);
    }

    @Test
    void getClientByIdBillAndThrownError(){
        Mockito.when(
          billDetailRepository.findClient(Mockito.anyInt())
        ).thenThrow(DataException.class);
        assertNull(billServiceImp.getClientByIdBill(1));
    }

    @Test
    public void doBestSellerReportWithAnyCorrectDate(){
        List<Object[]> reporte = new ArrayList();
        Mockito.when(
                billDetailRepository.getBestSeller(Mockito.any(Date.class),Mockito.any(Date.class))
        ).thenReturn(reporte);
        assertEquals(
                HttpStatus.OK,
                billServiceImp.getBestSeller("0001-01-01","2100-01-01").getStatusCode()
        );
    }

    @Test
    public void doBestSellerReportWithAnyWrongDate(){
        List<Object[]> reporte = new ArrayList();
        Mockito.when(
                billDetailRepository.getBestSeller(Mockito.any(Date.class), Mockito.any(Date.class))
        ).thenReturn(reporte);
        assertEquals(
                HttpStatus.BAD_REQUEST,
                billServiceImp.getBestSeller("malaFecha","malaFecha").getStatusCode()
        );
    }

    @Test
    public void doBestEarnerReportWithAnyCorrectDate(){
        List<Object[]> reporte = new ArrayList();
        Mockito.when(
                billDetailRepository.getBestEarner(Mockito.any(Date.class),Mockito.any(Date.class))
        ).thenReturn(reporte);
        assertEquals(
                HttpStatus.OK,
                billServiceImp.getBestEarner("0001-01-01","2100-01-01").getStatusCode()
        );
    }

    @Test
    public void doBestEarnerReportWithAnyWrongDate(){
        List<Object[]> reporte = new ArrayList();
        Mockito.when(
                billDetailRepository.getBestEarner(Mockito.any(Date.class), Mockito.any(Date.class))
        ).thenReturn(reporte);
        assertEquals(
                HttpStatus.BAD_REQUEST,
                billServiceImp.getBestEarner("malaFecha","malaFecha").getStatusCode()
        );
    }

    @Test
    public void doBillWithClientCreationFail(){
        Profile profile = Mockito.mock(Profile.class);
        BillData billData = new BillData(new Bill(1,null,profile,0,new Client(1,"anyName","anyAddress")), new ArrayList<>());
        Mockito.when(
                jwt.getProfileFromToken(Mockito.anyString())
        ).thenReturn(profile);
        Mockito.when(
                clientServiceImp.createClientIfNotExist(Mockito.any(Client.class))
        ).thenReturn(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false));
        assertEquals(
                HttpStatus.FAILED_DEPENDENCY,
                billServiceImp.doBill("anyToken",billData, jwt).getStatusCode()
        );
    }

    @Test
    public void doBillWithNoSaleFurnitures(){
        Profile profile = Mockito.mock(Profile.class);
        BillData billData = new BillData(new Bill(1,null,profile,0,new Client(1,"anyName","anyAddress")), new ArrayList<>());
        Mockito.when(
                jwt.getProfileFromToken(Mockito.anyString())
        ).thenReturn(profile);
        Mockito.when(
                clientServiceImp.createClientIfNotExist(Mockito.any(Client.class))
        ).thenReturn(ResponseEntity.status(HttpStatus.OK).body(true));
        Mockito.when(
                furnitureServiceImp.verifyOnSale(new ArrayList<>())
        ).thenReturn(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false));
        assertEquals(
                HttpStatus.PRECONDITION_FAILED,
                billServiceImp.doBill("anyToken",billData, jwt).getStatusCode()
        );
    }

    @Test
    public void doBillWithFailedBillDetails(){
        Profile profile = Mockito.mock(Profile.class);
        BillData billData = new BillData(new Bill(1,null,profile,0,new Client(1,"anyName","anyAddress")), new ArrayList<>());
        Mockito.when(
                jwt.getProfileFromToken(Mockito.anyString())
        ).thenReturn(profile);
        Mockito.when(
                clientServiceImp.createClientIfNotExist(Mockito.any(Client.class))
        ).thenReturn(ResponseEntity.status(HttpStatus.OK).body(true));
        Mockito.when(
                furnitureServiceImp.verifyOnSale(new ArrayList<>())
        ).thenReturn(ResponseEntity.status(HttpStatus.OK).body(true));
        Mockito.when(
                billRepository.save(billData.getBill())
        ).thenReturn(billData.getBill());
        Mockito.when(
                furnitureServiceImp.putSold(billData)
        ).thenReturn(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false));
        assertEquals(
                HttpStatus.INTERNAL_SERVER_ERROR,
                billServiceImp.doBill("anyToken",billData, jwt).getStatusCode()
        );
    }

    @Test
    public void doBillSuccesfully(){
        Profile profile = Mockito.mock(Profile.class);
        BillData billData = new BillData(new Bill(1,null,profile,0,new Client(1,"anyName","anyAddress")), new ArrayList<>());
        Mockito.when(
                jwt.getProfileFromToken(Mockito.anyString())
        ).thenReturn(profile);
        Mockito.when(
                clientServiceImp.createClientIfNotExist(Mockito.any(Client.class))
        ).thenReturn(ResponseEntity.status(HttpStatus.OK).body(true));
        Mockito.when(
                furnitureServiceImp.verifyOnSale(new ArrayList<>())
        ).thenReturn(ResponseEntity.status(HttpStatus.OK).body(true));
        Mockito.when(
                billRepository.save(billData.getBill())
        ).thenReturn(billData.getBill());
        Mockito.when(
                furnitureServiceImp.putSold(billData)
        ).thenReturn(ResponseEntity.status(HttpStatus.OK).body(true));
        assertEquals(
                HttpStatus.OK,
                billServiceImp.doBill("anyToken",billData, jwt).getStatusCode()
        );
    }
}