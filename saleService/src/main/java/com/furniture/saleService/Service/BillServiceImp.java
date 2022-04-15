package com.furniture.saleService.Service;

import com.furniture.saleService.Model.Client;
import com.furniture.saleService.Repository.BillDetailRepository;
import com.furniture.saleService.ServiceImp.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BillServiceImp implements BillService {
    @Autowired
    private BillDetailRepository billDetailRepository;

    @Override
    public List<Object[]> getDetailBill(int id) {
        try{
            return billDetailRepository.findAllDetailBils(id);
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public Object getClientByIdBill(int id) {
        try{
            return billDetailRepository.findClient(id);
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public ResponseEntity<Object> getBestEarner(String initialDate, String finalDate){
        Date date1, date2;
        try {
            date1 = new SimpleDateFormat("yyyy-MM-dd").parse(initialDate);
            date2 = new SimpleDateFormat("yyyy-MM-dd").parse(finalDate);
        } catch (ParseException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ha ocurrido un error al convertir una de las fechas");
        }
        Object body = this.billDetailRepository.getBestEarner(date1, date2);
        return ResponseEntity.status(HttpStatus.OK).body(body);
    }

    @Override
    public ResponseEntity<Object> getBestSeller(String initialDate, String finalDate) {
        Date date1, date2;
        try {
            date1 = new SimpleDateFormat("yyyy-MM-dd").parse(initialDate);
            date2 = new SimpleDateFormat("yyyy-MM-dd").parse(finalDate);
        } catch (ParseException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ha ocurrido un error al convertir una de las fechas");
        }
        Object body = this.billDetailRepository.getBestSeller(date1, date2);
        System.out.println(body.toString());
        return ResponseEntity.status(HttpStatus.OK).body(body);
    }
}
