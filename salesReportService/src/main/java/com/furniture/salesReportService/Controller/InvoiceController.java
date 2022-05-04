package com.furniture.salesReportService.Controller;

import com.furniture.salesReportService.Model.Bill;
import com.furniture.salesReportService.Model.BillDetails;
import com.furniture.salesReportService.ServiceImp.InvoiceServiceImp;
import com.furniture.salesReportService.ServiceImp.SaleFurnitureServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping("/sales/invoice")
public class InvoiceController {

    @Autowired
    private InvoiceServiceImp invoiceServiceImp;
    @Autowired
    private SaleFurnitureServiceImp saleFurnitureServiceImp;

    @GetMapping("/get-bill-cliente")
    public ResponseEntity<Object> getBillClient(
            @RequestParam Optional<Integer> billId,
            @RequestParam Optional<Integer> page
    ){
        Page<BillDetails> li = this.invoiceServiceImp.getBillsClient(billId, page);
        return ResponseEntity.ok().body(li);
    }


    @GetMapping("/get-return-client")
    public ResponseEntity<Page<BillDetails>> getReturnClient(
            @RequestParam Optional<Integer> nit,
            @RequestParam Optional<String> date1,
            @RequestParam Optional<String> date2,
            @RequestParam Optional<Integer> page
    ) throws ParseException {
        return ResponseEntity.ok().body(this.invoiceServiceImp.getReturnClient(nit, date1, date2, page));
    }

    @GetMapping("/get-sale-today")
    public ResponseEntity<Page<BillDetails>> getSalesToday(@RequestParam Optional<Integer> page) throws ParseException {

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
        Date date = formatter1.parse(dtf.format(LocalDateTime.now()));
        Optional<Date> d1 = Optional.of(date);

        return ResponseEntity.ok().body(this.invoiceServiceImp.getSalesToday(d1, page));
    }

    @GetMapping("/get-sales-clients")
    public ResponseEntity<Page<Bill>> getSalesClient(
            @RequestParam Optional<String> client,
            @RequestParam Optional<String> date1,
            @RequestParam Optional<String> date2,
            @RequestParam Optional<Integer> page
    ) throws ParseException {
        return ResponseEntity.ok().body(this.invoiceServiceImp.getBills(client, date1, date2, page));
    }

    @GetMapping("/get-earnings-total")
    public ResponseEntity<Object> getEarningsTotal(
            @RequestParam Optional<String> date1,
            @RequestParam Optional<String> date2
    ) throws ParseException {
        return ResponseEntity.ok().body(this.saleFurnitureServiceImp.getEarningsTotal(date1, date2));
    }
}
