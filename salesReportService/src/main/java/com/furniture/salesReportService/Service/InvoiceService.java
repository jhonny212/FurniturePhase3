package com.furniture.salesReportService.Service;

import com.furniture.salesReportService.Model.Bill;
import com.furniture.salesReportService.Model.BillDetails;
import org.springframework.data.domain.Page;

import java.text.ParseException;
import java.util.Date;
import java.util.Optional;

public interface InvoiceService {

    public Page<BillDetails> getBillsClient(Optional<Integer> nit, Optional<Integer> page);

    public Page<BillDetails> getReturnClient(Optional<Integer> nit, Optional<String> date1, Optional<String> date2, Optional<Integer> page) throws ParseException;

    public Page<BillDetails> getSalesToday(Optional<Date> date1, Optional<Integer> page);

    public Page<Bill> getBills(Optional<String> client, Optional<String> date1, Optional<String> date2, Optional<Integer> page) throws ParseException;

}
