package com.furniture.reportService.Controller;

import com.furniture.reportService.Model.BillDetails;
import com.furniture.reportService.ServiceImp.BillServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping("/admin/bill-details")
public class BillDetailController {

    @Autowired
    private BillServiceImp billServiceImp;

    @GetMapping("/report-sales-x-period")
    public ResponseEntity<Page<BillDetails>> getReportSalesXPeriod(
            @RequestParam Optional<String> date1,
            @RequestParam Optional<String> date2,
            @RequestParam Optional<Integer> page
    ) throws ParseException {
        return new ResponseEntity<>(this.billServiceImp.getReportSalesXperiod(date1, date2, page), HttpStatus.OK);
    }

}
