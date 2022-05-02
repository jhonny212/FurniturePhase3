package com.furniture.reportService.ServiceImp;

import com.furniture.reportService.Model.BillDetails;
import com.furniture.reportService.Model.Furniture;
import com.furniture.reportService.Repository.BillDetailsRepository;
import com.furniture.reportService.Repository.BillRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class BillServiceImpTest {

    @Mock
    private BillDetailsRepository billDetailsRepository;
    @Mock
    private BillRepository billRepository;
    @InjectMocks
    private BillServiceImp billServiceImp;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getReportSalesXperiod() throws ParseException {
        Page<BillDetails> list = Page.empty();
        Mockito.when(
                billDetailsRepository.findAllByBill_DateTimeBetweenAndDateReturnIsNull(Mockito.any(), Mockito.any(), Mockito.any(Pageable.class))
        ).thenReturn(list);
        assertEquals(billServiceImp.getReportSalesXperiod(Optional.of("2022-01-01"), Optional.of("2022-04-01"), Optional.of(0)), list);
    }

    @Test
    void getReportEarningsXPeriod() throws ParseException {
        Page<BillDetails> list = Page.empty();
        Mockito.when(
                billDetailsRepository.findAllByBill_DateTimeBetween(Mockito.any(), Mockito.any(), Mockito.any(Pageable.class))
        ).thenReturn(list);

        assertEquals(billServiceImp.getReportEarningsXPeriod(Optional.of("2022-01-01"), Optional.of("2022-04-01"), Optional.of(0)), list);
    }
}