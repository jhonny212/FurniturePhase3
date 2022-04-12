package com.furniture.saleService.Service;

import com.furniture.saleService.Model.BillDetails;
import com.furniture.saleService.Model.Furniture;
import com.furniture.saleService.Repository.BillDetailRepository;
import com.furniture.saleService.Repository.FurnitureRepository;
import com.furniture.saleService.ServiceImp.FurnitureService;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.Optional;

public class FurnitureServiceImp implements FurnitureService {
    @Autowired
    private BillDetailRepository billDetailRepository;
    @Autowired
    private FurnitureRepository furnitureRepository;

    @Override
    public boolean returnFurniture(int id, int code) {
        Date tmp = billDetailRepository.getDetail(id,code);
        if(tmp!=null){
            LocalDate date = tmp.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
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
                tmp.get().setCostLost(lost);
                tmp.get().setDateReturn(date);
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
}
