package com.furniture.reportService.ServiceImp;

import com.furniture.reportService.Service.BillDetailsRepository;
import com.furniture.reportService.Service.FurnitureReportController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

@Service
public class FurnitureServiceImp implements FurnitureReportController {
    @Autowired
    BillDetailsRepository billDetailsRepository;
    @Override
    public List<Object[]> getLost(Optional<String> date1, Optional<String> date2) {
        boolean valid = date1.isPresent() && date2.isPresent();
        if(valid){
            SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
            try{
                return this.billDetailsRepository.getLost2(0,format.parse(date1.get()),format.parse(date2.get()));
            }catch (Exception e){
                return this.billDetailsRepository.getLost1(0);
            }
        }else{
            return  this.billDetailsRepository.getLost1(0);
        }
    }
}
