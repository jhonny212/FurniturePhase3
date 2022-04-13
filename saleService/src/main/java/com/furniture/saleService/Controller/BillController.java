package com.furniture.saleService.Controller;

import com.furniture.saleService.Service.BillServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
}
