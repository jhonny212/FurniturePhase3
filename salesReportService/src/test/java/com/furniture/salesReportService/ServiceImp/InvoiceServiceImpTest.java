package com.furniture.salesReportService.ServiceImp;

import com.furniture.salesReportService.Model.Bill;
import com.furniture.salesReportService.Model.BillDetails;
import com.furniture.salesReportService.Model.Client;
import com.furniture.salesReportService.Model.Profile;
import com.furniture.salesReportService.Repository.BillDetailsRepository;
import com.furniture.salesReportService.Repository.BillRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class InvoiceServiceImpTest {

    @Mock
    private BillRepository billRepository;
    @Mock
    private BillDetailsRepository billDetailsRepository;
    @InjectMocks
    private InvoiceServiceImp invoiceServiceImp;
    Bill bill;
    BillDetails billDetails;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        bill = new Bill();
        billDetails = new BillDetails();
    }

    @Test
    void getBillsClientAndReturnListOfBillDetails() {
        Page<BillDetails> list = Page.empty();
        Mockito.when(
                billDetailsRepository.findAllByBill(Mockito.any(), Mockito.any(Pageable.class))
        ).thenReturn(list);
        assertEquals(invoiceServiceImp.getBillsClient(Optional.of(1), Optional.empty()),list);
    }

    @Test
    void getReturnClientAndReturnListOfBillDetails() throws ParseException {
        Page<BillDetails> list = Page.empty();
        Mockito.when(
                billDetailsRepository.findAllByBill_Client_IdAndDateReturnNotNullAndDateReturnBetween(Mockito.anyInt(), Mockito.any(), Mockito.any(), Mockito.any(Pageable.class))
        ).thenReturn(list);
        assertEquals(invoiceServiceImp.getReturnClient(Optional.of(1), Optional.of("2022-01-15"),Optional.of("2022-04-15"), Optional.of(0)), list);
    }

    @Test
    void getSalesTodayAndReturnListOfBillDetails() {
        Page<BillDetails> list = Page.empty();
        Mockito.when(
                billDetailsRepository.findAllByBill_DateTime(Mockito.any(), Mockito.any(Pageable.class))
        ).thenReturn(list);
        assertEquals(invoiceServiceImp.getSalesToday(Optional.of(new Date()), Optional.of(1)), list);
    }

    @Test
    void getBillsAndReturnListOfBill() throws ParseException {
        Page<Bill> list = Page.empty();
        Mockito.when(
                billRepository.findByClient_NameContainsAndDateTimeBetween(Mockito.anyString(), Mockito.any(), Mockito.any(), Mockito.any(Pageable.class))
        ).thenReturn(list);
        assertEquals(invoiceServiceImp.getBills(Optional.of("1"), Optional.of("2022-01-01"), Optional.of("2022-03-01"), Optional.of(1)),list);
    }
}