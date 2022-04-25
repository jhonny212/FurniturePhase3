package com.furniture.saleService.Service;

import com.furniture.saleService.Model.BillData;
import com.furniture.saleService.Model.BillDetails;
import com.furniture.saleService.Model.Furniture;
import com.furniture.saleService.Model.OnSaleData;
import com.furniture.saleService.Repository.BillDetailRepository;
import com.furniture.saleService.Repository.FurnitureRepository;
import com.furniture.saleService.ServiceImp.FurnitureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Optional;

@Service
public class FurnitureServiceImp implements FurnitureService {

    @Autowired
    private BillDetailRepository billDetailRepository;
    @Autowired
    private FurnitureRepository furnitureRepository;

    @Override
    public boolean returnFurniture(int id, int code) {
        Date tmp = billDetailRepository.getDetail(id,code);
        if(tmp!=null){
            LocalDate date = LocalDate.parse(tmp.toString());
            if(getDiffDay(date)){
                Furniture fun = furnitureRepository.findById(code).orElse(null);
                if(fun!=null){
                    return repayment(fun,id,tmp);
                }
            }
        }
        return false;
    }

    boolean getDiffDay(LocalDate Dateinicio){
        LocalDate actual = LocalDate.now();
        int dias = actual.getDayOfYear()-Dateinicio.getDayOfYear();
        return dias<7 && dias>=0;
    }

    boolean repayment(Furniture furniture,int id,Date date){
        double cost = furniture.getCost();
        double lost = cost*1/3;
        lost = Math.round((lost)*100.0)/100.0;
        furniture.setCost(cost+lost);
        furniture.setPrice(furniture.getPrice()+lost);
        furniture.setStatus(0);
        furniture = furnitureRepository.save(furniture);
        if(furniture.getPlan()!=null){
            Optional<BillDetails> tmp = this.billDetailRepository.findById(id);
            if(tmp.isPresent()){
                LocalDate localDate = LocalDate.now();
                ZoneId defaultZoneId = ZoneId.systemDefault();
                Date d = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());;
                tmp.get().setCostLost(lost);
                tmp.get().setDateReturn(d);
                this.billDetailRepository.save(tmp.get());
                return true;
            }else{
                furniture.setCost(cost);
                furniture.setPrice(furniture.getPrice()-lost);
                furniture.setStatus(1);
                furnitureRepository.save(furniture);
            }
        }
        return false;
    }

    @Override
    public ResponseEntity<Boolean> putFurnitureOnSale(OnSaleData onSaleData){
        Furniture furniture = this.furnitureRepository.findById(onSaleData.getCode()).orElse(null);
        if(furniture!=null){
            furniture.setPrice(onSaleData.getPrice());
            furniture.setStatus(1);
            this.furnitureRepository.save(furniture);
            return ResponseEntity.status(HttpStatus.OK).body(true);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(false);
        }
    }

    @Override
    public ResponseEntity<Page<Furniture>> getFurnituresOnSale(Optional<Integer> page, Optional<String> name) {
        return this.getFurnituresByStatus(page, name, 1);
    }

    @Override
    public ResponseEntity<Page<Furniture>> getFurnituresOnStorage(Optional<Integer> page, Optional<String> name) {
        return this.getFurnituresByStatus(page, name, 0);
    }

    public ResponseEntity<Page<Furniture>> getFurnituresByStatus(Optional<Integer> page, Optional<String> name, Integer status){
        return ResponseEntity.status(HttpStatus.OK).body(this.furnitureRepository.findByStatusAndNameContainsIgnoreCase(
                status,
                name.orElse(""),
                PageRequest.of(page.orElse(0), 5)
        ));
    }

    @Override
    public ResponseEntity<Boolean> verifyOnSale(ArrayList<BillDetails> details) {
        for(BillDetails detail : details){
            Furniture furniture = this.furnitureRepository.findById(detail.getFurniture().getCode()).orElse(null);
            try{
                if(furniture.getStatus()==0 || furniture.getStatus() == 2) return ResponseEntity.status(HttpStatus.OK).body(false);
            }catch(Exception ex){
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @Override
    @Transactional
    public ResponseEntity<Boolean> putSold(BillData billData){
        for(BillDetails detail : billData.getDetails()){
            try{
                Furniture furniture = this.furnitureRepository.findById(detail.getFurniture().getCode()).orElse(null);
                furniture.setStatus(2);
                this.furnitureRepository.save(furniture);

                detail.setBill(billData.getBill());
                this.billDetailRepository.save(detail);
            }catch(Exception ex){
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(true);
    }
}
