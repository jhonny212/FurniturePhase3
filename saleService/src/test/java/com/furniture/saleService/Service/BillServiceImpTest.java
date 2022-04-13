package com.furniture.saleService.Service;

import com.furniture.saleService.Repository.BillDetailRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import org.hibernate.exception.DataException;
class BillServiceImpTest {
    @Mock
    private BillDetailRepository billDetailRepository;
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
}