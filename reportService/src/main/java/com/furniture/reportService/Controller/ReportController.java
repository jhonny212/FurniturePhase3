package com.furniture.reportService.Controller;

import com.furniture.reportService.Model.BillDetails;
import com.furniture.reportService.Model.Furniture;
import com.furniture.reportService.ServiceImp.BillServiceImp;
import com.furniture.reportService.ServiceImp.ReportServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin/report")
public class ReportController {

    @Autowired
    private BillServiceImp billServiceImp;
    @Autowired
    private ReportServiceImpl reportServiceImpl;

    @GetMapping("/report-best-seller-x-period")
    public ResponseEntity<Object> getBestSellerInXPeriod(@RequestParam(value="date1") Optional<String> initialDate, @RequestParam(value="date2") Optional<String> finalDate, @RequestHeader("Authorization") String token){
        return this.reportServiceImpl.getBestSellerInXPeriod(initialDate, finalDate, new RestTemplate(), token);
    }

    @GetMapping("/report-best-earner-x-period")
    public ResponseEntity<Object> getBestEarnerInXPeriod(@RequestParam(value="date1") Optional<String> initialDate, @RequestParam(value="date2") Optional<String> finalDate, @RequestHeader("Authorization") String token){
        return this.reportServiceImpl.getBestEarnerInXPeriod(initialDate, finalDate, new RestTemplate(), token);
    }

    @GetMapping("/report-max-furniture-x-period")
    public ResponseEntity<List<Furniture>> getMaxFurnitureXPeriod(
            @RequestParam Optional<String> date1,
            @RequestParam Optional<String> date2
    ) throws ParseException {
        return ResponseEntity.ok().body(this.reportServiceImpl.getReportMaxFurnitureXPeriod(date1, date2));
    }

    @GetMapping("/report-min-furniture-x-period")
    public ResponseEntity<List<Furniture>> getMinFurnitureXPeriod(
            @RequestParam Optional<String> date1,
            @RequestParam Optional<String> date2
    ) throws ParseException {
        return ResponseEntity.ok().body(this.reportServiceImpl.getReportMinFurnitureXPeriod(date1, date2));
    }

    @GetMapping("/report-earnings-x-period")
    public Page<BillDetails> getEarningsXPeriod(
            @RequestParam Optional<String> date1,
            @RequestParam Optional<String> date2,
            @RequestParam Optional<Integer> page
    ) throws ParseException {
        return this.billServiceImp.getReportEarningsXPeriod(date1, date2, page);
    }
}
