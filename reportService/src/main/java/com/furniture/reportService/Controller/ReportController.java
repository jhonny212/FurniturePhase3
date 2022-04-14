package com.furniture.reportService.Controller;

import com.furniture.reportService.ServiceImp.ReportServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/admin/report")
public class ReportController {

    @Autowired
    private ReportServiceImpl reportServiceImpl;

    @GetMapping("/report-best-seller-x-period")
    public ResponseEntity<Object> getBestSellerInXPeriod(@RequestParam(value="date1") Optional<String> initialDate, @RequestParam(value="date2") Optional<String> finalDate){
        return this.reportServiceImpl.getBestSellerInXPeriod(initialDate, finalDate);
    }

    @GetMapping("/report-best-earner-x-period")
    public ResponseEntity<Object> getBestEarnerInXPeriod(@RequestParam(value="date1") Optional<String> initialDate, @RequestParam(value="date2") Optional<String> finalDate){
        return this.reportServiceImpl.getBestEarnerInXPeriod(initialDate, finalDate);
    }
}
