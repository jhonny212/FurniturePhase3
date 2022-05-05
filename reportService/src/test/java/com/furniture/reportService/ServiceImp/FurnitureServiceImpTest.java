package com.furniture.reportService.ServiceImp;

import com.furniture.reportService.Repository.BillDetailsRepository;
import com.furniture.reportService.Repository.FurnitureRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.DataIntegrityViolationException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class FurnitureServiceImpTest {

    @Mock
    BillDetailsRepository billDetailsRepository;
    @InjectMocks
    private  FurnitureServiceImp furnitureServiceImp;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getLostAndReturnValidValue() throws ParseException {
        Optional<String> d1 = Optional.of("2022-04-10");
        List<Object[]> list = new ArrayList<>();
        Mockito.when(
                billDetailsRepository.getLost2(
                        Mockito.anyDouble(),Mockito.any(Date.class),Mockito.any(Date.class)
                )
        ).thenReturn(list);
        assertEquals(furnitureServiceImp.getLost(d1,d1),list);
    }

    @Test
    void getLostAndReturnValidValueThrownError() throws ParseException {
        Optional<String> d1 = Optional.of("2022-04-10");
        List<Object[]> list = new ArrayList<>();
        Mockito.when(
                billDetailsRepository.getLost2(
                        Mockito.anyDouble(),Mockito.any(Date.class),Mockito.any(Date.class)
                )
        ).thenThrow(DataIntegrityViolationException.class);
        Mockito.when(
                billDetailsRepository.getLost1(Mockito.anyDouble())
        ).thenReturn(list);
        assertEquals(furnitureServiceImp.getLost(d1,d1),list);
    }

    @Test
    void testGetLostAndReturnLost1() {
        Optional<String> d1 = Optional.empty();
        List<Object[]> list = new ArrayList<>();
        Mockito.when(
                billDetailsRepository.getLost1(Mockito.anyDouble())
        ).thenReturn(list);
        assertEquals(furnitureServiceImp.getLost(d1,d1),list);
    }
}