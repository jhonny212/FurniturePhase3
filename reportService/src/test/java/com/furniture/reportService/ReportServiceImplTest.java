package com.furniture.reportService;

import com.furniture.reportService.ServiceImp.ReportServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ReportServiceImplTest {

    @InjectMocks
    ReportServiceImpl reportServiceImp;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void contextLoads() {
    }

    @Test
    void doBestSellerReportWithAnyDate(){
        RestTemplate restTemplate = Mockito.mock (RestTemplate.class);
        Object report = new Object();
        Mockito.when(
                restTemplate.exchange(Mockito.anyString(), Mockito.any(HttpMethod.class), Mockito.any(HttpEntity.class), ArgumentMatchers.<Class<Object>>any())
        ).thenReturn(ResponseEntity.status(HttpStatus.OK).body(report));
        // Caso 1: sin ninguna de las dos fechas
        assertEquals(
                ResponseEntity.status(HttpStatus.OK).body(report),
                reportServiceImp.getBestSellerInXPeriod(Optional.empty(), Optional.empty(), restTemplate, "anyToken")
        );
    }

    @Test
    public void doBestSellerReportWithFirstDate(){
        RestTemplate restTemplate = Mockito.mock (RestTemplate.class);
        Object report = new Object();
        Mockito.when(
                restTemplate.exchange(Mockito.anyString(), Mockito.any(HttpMethod.class), Mockito.any(HttpEntity.class), ArgumentMatchers.<Class<Object>>any())        ).thenReturn(ResponseEntity.status(HttpStatus.OK).body(report));
        // Caso 2: con la primer fecha
        assertEquals(
                ResponseEntity.status(HttpStatus.OK).body(report),
                reportServiceImp.getBestSellerInXPeriod(Optional.of("2022-01-10"), Optional.empty(), restTemplate, "anyToken")
        );
    }

    @Test
    public void doBestSellerReportWithSecondDate(){
        RestTemplate restTemplate = Mockito.mock (RestTemplate.class);
        Object report = new Object();
        Mockito.when(
                restTemplate.exchange(Mockito.anyString(), Mockito.any(HttpMethod.class), Mockito.any(HttpEntity.class), ArgumentMatchers.<Class<Object>>any())        ).thenReturn(ResponseEntity.status(HttpStatus.OK).body(report));
        // Caso 3: con la segunda fecha
        assertEquals(
                ResponseEntity.status(HttpStatus.OK).body(report),
                reportServiceImp.getBestSellerInXPeriod(Optional.empty(), Optional.of("2022-01-10"), restTemplate, "anyToken")
        );
    }

    @Test
    public void doBestSellerReportWithTwoDates(){
        RestTemplate restTemplate = Mockito.mock (RestTemplate.class);
        Object report = new Object();
        Mockito.when(
                restTemplate.exchange(Mockito.anyString(), Mockito.any(HttpMethod.class), Mockito.any(HttpEntity.class), ArgumentMatchers.<Class<Object>>any())        ).thenReturn(ResponseEntity.status(HttpStatus.OK).body(report));
        // Caso 4: con ambas fechas
        assertEquals(
                ResponseEntity.status(HttpStatus.OK).body(report),
                reportServiceImp.getBestSellerInXPeriod(Optional.of("2022-01-01"), Optional.of("2022-01-10"), restTemplate, "anyToken")
        );
    }

    @Test
    void doBestEarnerReportWithAnyDate(){
        RestTemplate restTemplate = Mockito.mock (RestTemplate.class);
        Object report = new Object();
        Mockito.when(
                restTemplate.exchange(Mockito.anyString(), Mockito.any(HttpMethod.class), Mockito.any(HttpEntity.class), ArgumentMatchers.<Class<Object>>any())        ).thenReturn(ResponseEntity.status(HttpStatus.OK).body(report));
        // Caso 1: sin ninguna de las dos fechas
        assertEquals(
                ResponseEntity.status(HttpStatus.OK).body(report),
                reportServiceImp.getBestEarnerInXPeriod(Optional.empty(), Optional.empty(), restTemplate, "anyToken")
        );
    }

    @Test
    public void doBestEarnerReportWithFirstDate(){
        RestTemplate restTemplate = Mockito.mock (RestTemplate.class);
        Object report = new Object();
        Mockito.when(
                restTemplate.exchange(Mockito.anyString(), Mockito.any(HttpMethod.class), Mockito.any(HttpEntity.class), ArgumentMatchers.<Class<Object>>any())        ).thenReturn(ResponseEntity.status(HttpStatus.OK).body(report));
        // Caso 2: con la primer fecha
        assertEquals(
                ResponseEntity.status(HttpStatus.OK).body(report),
                reportServiceImp.getBestEarnerInXPeriod(Optional.of("2022-01-10"), Optional.empty(), restTemplate, "anyToken")
        );
    }

    @Test
    public void doBestEarnerReportWithSecondDate(){
        RestTemplate restTemplate = Mockito.mock (RestTemplate.class);
        Object report = new Object();
        Mockito.when(
                restTemplate.exchange(Mockito.anyString(), Mockito.any(HttpMethod.class), Mockito.any(HttpEntity.class), ArgumentMatchers.<Class<Object>>any())        ).thenReturn(ResponseEntity.status(HttpStatus.OK).body(report));
        // Caso 3: con la segunda fecha
        assertEquals(
                ResponseEntity.status(HttpStatus.OK).body(report),
                reportServiceImp.getBestEarnerInXPeriod(Optional.empty(), Optional.of("2022-01-10"), restTemplate, "anyToken")
        );
    }

    @Test
    public void doBestEarnerReportWithTwoDates(){
        RestTemplate restTemplate = Mockito.mock (RestTemplate.class);
        Object report = new Object();
        Mockito.when(
                restTemplate.exchange(Mockito.anyString(), Mockito.any(HttpMethod.class), Mockito.any(HttpEntity.class), ArgumentMatchers.<Class<Object>>any())        ).thenReturn(ResponseEntity.status(HttpStatus.OK).body(report));
        // Caso 4: con ambas fechas
        assertEquals(
                ResponseEntity.status(HttpStatus.OK).body(report),
                reportServiceImp.getBestEarnerInXPeriod(Optional.of("2022-01-01"), Optional.of("2022-01-10"), restTemplate, "anyToken")
        );
    }
}
