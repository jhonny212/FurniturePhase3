package com.furniture.saleService.Service;

import com.furniture.saleService.Model.*;
import com.furniture.saleService.Repository.BillDetailRepository;
import com.furniture.saleService.Repository.BillRepository;
import com.furniture.saleService.ServiceImp.BillService;
import com.furniture.saleService.Util.Utilities;
import com.furniture.saleService.config.JWTAuthorizationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class BillServiceImp implements BillService {

    @Autowired
    private BillDetailRepository billDetailRepository;
    @Autowired
    private BillRepository billRepository;
    @Autowired
    private ClientServiceImp clientServiceImp;
    @Autowired
    private FurnitureServiceImp furnitureServiceImp;

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
        return ResponseEntity.status(HttpStatus.OK).body(body);
    }

    @Override
    @Transactional
    public ResponseEntity<Bill> doBill(String token, BillData billData) {
        JWTAuthorizationFilter jwt = new JWTAuthorizationFilter();
        billData.getBill().setDateTime(Utilities.getActualDate());
        billData.getBill().setProfile(jwt.getProfileFromToken(token));
        ResponseEntity<Boolean> createClientIfNotExist = clientServiceImp.createClientIfNotExist(billData.getBill().getClient());

        if(createClientIfNotExist.getStatusCode().equals(HttpStatus.OK)){
            ResponseEntity<Boolean> verifyFurnituresOnSale = furnitureServiceImp.verifyOnSale(billData.getDetails());

            if(verifyFurnituresOnSale.getStatusCode().equals(HttpStatus.OK) && verifyFurnituresOnSale.getBody()){
                this.billRepository.save(billData.getBill());
                ResponseEntity<Boolean> createBillDetails = furnitureServiceImp.putSold(billData);

                if(createBillDetails.getStatusCode().equals(HttpStatus.OK)){
                    return ResponseEntity.status(HttpStatus.OK).body(billData.getBill());

                }else{
                    this.billRepository.delete(billData.getBill());
                    return ResponseEntity.status(createBillDetails.getStatusCode()).body(null);
                }
            }else{
                return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(null);
            }
        }else{
            return ResponseEntity.status(HttpStatus.FAILED_DEPENDENCY).body(null);
        }
    }
}
