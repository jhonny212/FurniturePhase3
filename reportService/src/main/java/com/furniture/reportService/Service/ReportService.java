package com.furniture.reportService.Service;

import com.furniture.reportService.Model.Furniture;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ReportService {

    public ResponseEntity<Object> getBestSellerInXPeriod(Optional<String> initialDate, Optional<String> finalDate, RestTemplate restTemplate);
    public ResponseEntity<Object> getBestEarnerInXPeriod(Optional<String> initialDate, Optional<String> finalDate, RestTemplate restTemplate);
    public List<Furniture> getReportMinFurnitureXPeriod(Optional<String> date1, Optional<String> date2) throws ParseException;
    public List<Furniture> getReportMaxFurnitureXPeriod(Optional<String> date1, Optional<String> date2) throws ParseException;
    public Object getReportEarningsXPeriod(Optional<Date> date1, Optional<Date> date2, Optional<Integer> page);
}
