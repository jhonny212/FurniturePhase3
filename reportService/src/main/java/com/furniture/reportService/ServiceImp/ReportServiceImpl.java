package com.furniture.reportService.ServiceImp;

import com.furniture.reportService.Model.reports.BestEarnerReport;
import com.furniture.reportService.Service.ReportService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.function.EntityResponse;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ReportServiceImpl implements ReportService {

    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public ResponseEntity<Object> getBestSellerInXPeriod(Optional<String> initialDate, Optional<String> finalDate) {
        ResponseEntity<Object> response = this.restTemplate.getForEntity("http://localhost:8085/sale/bill/best-seller?initialDate="+initialDate.orElse("0001-01-01")+"&finalDate="+finalDate.orElse("2100-01-01"), Object.class);
        if(response.getStatusCode().equals(HttpStatus.OK)) return ResponseEntity.status(HttpStatus.OK).body(response.getBody());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response.getBody());
    }

    @Override
    public ResponseEntity<Object> getBestEarnerInXPeriod(Optional<String> initialDate, Optional<String> finalDate) {
        ResponseEntity<Object> response = this.restTemplate.getForEntity("http://localhost:8085/sale/bill/best-earner?initialDate="+initialDate.orElse("0001-01-01")+"&finalDate="+finalDate.orElse("2100-01-01"), Object.class);
        if(response.getStatusCode().equals(HttpStatus.OK)) return ResponseEntity.status(HttpStatus.OK).body(response.getBody());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response.getBody());
    }
}
