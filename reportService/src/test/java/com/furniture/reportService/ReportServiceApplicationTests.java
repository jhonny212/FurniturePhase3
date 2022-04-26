package com.furniture.reportService;

import com.furniture.reportService.ServiceImp.ReportServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class ReportServiceApplicationTests {

	@Mock
	RestTemplate restTemplate;
	@InjectMocks
	ReportServiceImpl reportServiceImp;

	@BeforeEach
	void setUp(){
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void contextLoads() {
	}

	/* @Test
	void doBestSellerReportWith(){
		Object report = new Object();
		Mockito.when(
				restTemplate.getForEntity(Mockito.anyString(), Object.class)
		).thenReturn(ResponseEntity.status(HttpStatus.OK).body(report));
		// Caso 1: dos fechas
		Optional<String> date1 = Optional.empty();
		Optional<String> date2 = Optional.empty();
		assertEquals(
			ResponseEntity.status(HttpStatus.OK).body(report),
			reportServiceImp.getBestSellerInXPeriod(date1,date2)
		);
	}*/

}
