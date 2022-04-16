package com.furniture.reportService.ServiceImp;

import com.furniture.reportService.Model.BillDetails;
import com.furniture.reportService.Repository.BillDetailsRepository;
import com.furniture.reportService.Repository.BillRepository;
import com.furniture.reportService.Service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@Service
public class BillServiceImp implements BillService {

    @Autowired
    private BillDetailsRepository billDetailsRepository;
    @Autowired
    private BillRepository billRepository;

    @Override
    public Page<BillDetails> getReportSalesXperiod(Optional<String> date1, Optional<String> date2, Optional<Integer> page) throws ParseException {
        SimpleDateFormat formatter1=new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat formatter2=new SimpleDateFormat("yyyy-MM-dd");
        Date dates1 = formatter1.parse("0001-01-01");
        Date dates2 = formatter2.parse("9999-01-01");
        if (!date1.isPresent()){
            dates1 = formatter1.parse(date1.get());
        }

        if (!date2.isPresent()){
            dates2 = formatter2.parse(date2.get());
        }

        Optional<Date> d1 = Optional.of(dates1);
        Optional<Date> d2 = Optional.of(dates2);

        return this.billDetailsRepository.findAllByBill_DateTimeBetweenAndDateReturnIsNull(
                d1.get(),
                d2.get(),
                PageRequest.of(page.orElse(0), 100)
        );
    }

    @Override
    public Page<BillDetails> getReportEarningsXPeriod(Optional<String> date1, Optional<String> date2, Optional<Integer> page) throws ParseException {
        SimpleDateFormat formatter1=new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat formatter2=new SimpleDateFormat("yyyy-MM-dd");
        Date dates1 = formatter1.parse("0001-01-01");
        Date dates2 = formatter2.parse("2100-01-01");
        if (!date1.isEmpty()){
            dates1 = formatter1.parse(date1.get());
        }

        if (!date2.isEmpty()){
            dates2 = formatter2.parse(date2.get());
        }

        Optional<Date> d1 = Optional.of(dates1);
        Optional<Date> d2 = Optional.of(dates2);

        return this.billDetailsRepository.findAllByBill_DateTimeBetween(
                d1.get(),
                d2.get(),
                PageRequest.of(page.orElse(0), 100)
        );
    }

}
