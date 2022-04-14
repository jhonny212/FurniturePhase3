package com.furniture.saleService.Controller;

import com.furniture.saleService.Service.BillServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("sale/")
public class BillController {
    @Autowired
    private BillServiceImp billServiceImp;

    @GetMapping("{id}")
    public ResponseEntity<List<Object[]>> getDetailBill (
            @PathVariable("id") int id
    ){
        List<Object[]> obj = billServiceImp.getDetailBill(id);
        if(obj==null){
            return ResponseEntity.internalServerError().body(null);
        }
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping("client/{id}")
    public ResponseEntity<Object> getDetailClientByBillId(@PathVariable("id") int id){
        Object obj = billServiceImp.getClientByIdBill(id);
        if (obj==null){
            return ResponseEntity.internalServerError().body(null);
        }
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping("/bill/best-earner")
    public ResponseEntity<Object> getBestEarner(@RequestParam String initialDate, @RequestParam String finalDate){
        return this.billServiceImp.getBestEarner(initialDate, finalDate);
    }

    @GetMapping("/bill/best-seller")
    public ResponseEntity<Object> getBestSeller(@RequestParam String initialDate, @RequestParam String finalDate){
        return this.billServiceImp.getBestSeller(initialDate, finalDate);
    }
}
