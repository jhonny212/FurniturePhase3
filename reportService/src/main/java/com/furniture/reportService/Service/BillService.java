package com.furniture.reportService.Service;

import com.furniture.reportService.Model.Bill;
import com.furniture.reportService.Model.BillDetails;
import org.springframework.data.domain.Page;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface BillService {

    public Page<BillDetails> getReportSalesXperiod(Optional<String> date1, Optional<String> date2, Optional<Integer> page) throws ParseException;
    public Page<BillDetails> getReportEarningsXPeriod(Optional<String> date1, Optional<String> date2, Optional<Integer> page) throws ParseException;

}
