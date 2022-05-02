package com.furniture.reportService.ServiceImp;

import com.furniture.reportService.Model.Furniture;
import com.furniture.reportService.Repository.FurnitureRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ReportServiceImplTest {

    @Mock
    private FurnitureRepository furnitureRepository;
    @InjectMocks
    private  ReportServiceImpl reportServiceImp;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getBestSellerInXPeriod() {
    }

    @Test
    void getBestEarnerInXPeriod() {
    }

    @Test
    void getReportMinFurnitureXPeriod() throws ParseException {
        List<Furniture> list = new ArrayList<>();
        List<Double> list1 = new ArrayList<>();
        Mockito.when(
                furnitureRepository.findMinFurniture(Mockito.any(), Mockito.any())
        ).thenReturn(list);

        Mockito.when(
                furnitureRepository.findMinFurnitureNum(Mockito.any(), Mockito.any())
        ).thenReturn(list1);

        assertEquals(reportServiceImp.getReportMinFurnitureXPeriod(Optional.of("2022-01-01"), Optional.of("2022-01-01")), list);
    }

    @Test
    void getReportMaxFurnitureXPeriod() throws ParseException {
        List<Furniture> list = new ArrayList<>();
        List<Double> list1 = new ArrayList<>();
        Mockito.when(
                furnitureRepository.findMaxFurniture(Mockito.any(), Mockito.any())
        ).thenReturn(list);

        Mockito.when(
                furnitureRepository.findMaxFurnitureNum(Mockito.any(), Mockito.any())
        ).thenReturn(list1);

        assertEquals(reportServiceImp.getReportMaxFurnitureXPeriod(Optional.of("2022-01-01"), Optional.of("2022-01-01")), list);
    }

    @Test
    void getReportEarningsXPeriod() {
        Object obj = new Object();
        List<Furniture> list = new ArrayList<>();
        List<Double> list1 = new ArrayList<>();
        Mockito.when(
                furnitureRepository.findMinFurniture(Mockito.any(), Mockito.any())
        ).thenReturn(list);

        Mockito.when(
                furnitureRepository.findMinFurnitureNum(Mockito.any(), Mockito.any())
        ).thenReturn(list1);

        assertNull(reportServiceImp.getReportEarningsXPeriod(Optional.of(new Date()), Optional.of(new Date()), Optional.of(0)));
    }
}