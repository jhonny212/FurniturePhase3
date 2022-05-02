package com.furniture.salesReportService.ServiceImp;

import com.furniture.salesReportService.Repository.BillDetailsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.text.ParseException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class SaleFurnitureServiceImpTest {

    @Mock
    private BillDetailsRepository billDetailsRepository;
    @InjectMocks
    private SaleFurnitureServiceImp saleFurnitureServiceImp;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getEarningsTotalReturnListaEarnings() throws ParseException {
        Object obj = new Object();
        Mockito.when(
                billDetailsRepository.findEarnings(Mockito.any(), Mockito.any())
        ).thenReturn(obj);
        assertEquals(saleFurnitureServiceImp.getEarningsTotal(Optional.of("2022-01-01"), Optional.of("2022-04-01")), obj);
    }
}