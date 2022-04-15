package com.furniture.salesReportService.ServiceImp;

import com.furniture.salesReportService.Repository.BillDetailsRepository;
import com.furniture.salesReportService.Service.SaleFurnitureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@Service
public class SaleFurnitureServiceImp implements SaleFurnitureService {

    @Autowired
    private BillDetailsRepository billDetailsRepository;

    @Override
    public Object getEarningsTotal(Optional<String> date1, Optional<String> date2) throws ParseException {
        SimpleDateFormat formatter1=new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat formatter2=new SimpleDateFormat("yyyy-MM-dd");
        Date dates1 = formatter1.parse("0001-01-01");
        Date dates2 = formatter2.parse("9999-01-01");
        if (!date1.isEmpty()){
            dates1 = formatter1.parse(date1.get());
        }

        if (!date2.isEmpty()){
            dates2 = formatter2.parse(date2.get());
        }

        Optional<Date> d1 = Optional.of(dates1);
        Optional<Date> d2 = Optional.of(dates2);

        Object obj = this.billDetailsRepository.findEarnings(
                d1.get(),
                d2.get()
        );

        return obj;
    }
}
