package com.furniture.salesReportService.ServiceImp;

import com.furniture.salesReportService.Model.Bill;
import com.furniture.salesReportService.Model.BillDetails;
import com.furniture.salesReportService.Repository.BillDetailsRepository;
import com.furniture.salesReportService.Repository.BillRepository;
import com.furniture.salesReportService.Service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@Service
public class InvoiceServiceImp implements InvoiceService {

    @Autowired
    private BillRepository billRepository;
    @Autowired
    private BillDetailsRepository billDetailsRepository;

    @Override
    public Page<BillDetails> getBillsClient(Optional<Integer> billId, Optional<Integer> page){
        Bill bill = new Bill();
        bill.setId(billId.orElse(0));
        Page<BillDetails> l = this.billDetailsRepository.findAllByBill(bill, PageRequest.of(page.orElse(0),10));
        System.out.println(l.getContent());
        return l;
    }

    @Override
    public Page<BillDetails> getReturnClient(Optional<Integer> nit, Optional<String> date1, Optional<String> date2, Optional<Integer> page) throws ParseException {
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
        Page<BillDetails> pag = this.billDetailsRepository.findAllByBill_Client_IdAndDateReturnNotNullAndDateReturnBetween(
            nit.get(),
            d1.get(), d2.get(),
            PageRequest.of(page.orElse(0), 25)
        );
        return  pag;
    }

    @Override
    public Page<BillDetails> getSalesToday(Optional<Date> date1, Optional<Integer> page){
        return this.billDetailsRepository.findAllByBill_DateTime(date1.get(), PageRequest.of(page.orElse(0), 25));
    }

    @Override
    public Page<Bill> getBills(Optional<String> client, Optional<String> date1, Optional<String> date2, Optional<Integer> page) throws ParseException {
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

        return this.billRepository.findByClient_NameContainsAndDateTimeBetween(
            client.get(),
            d1.get(),
            d2.get(),
            PageRequest.of(page.orElse(0), 25)
        );
    }

}
