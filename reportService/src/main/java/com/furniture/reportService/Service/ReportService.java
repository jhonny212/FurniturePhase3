package com.furniture.reportService.Service;

import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface ReportService {

    public ResponseEntity<Object> getBestSellerInXPeriod(Optional<String> initialDate, Optional<String> finalDate);
    public ResponseEntity<Object> getBestEarnerInXPeriod(Optional<String> initialDate, Optional<String> finalDate);
}
