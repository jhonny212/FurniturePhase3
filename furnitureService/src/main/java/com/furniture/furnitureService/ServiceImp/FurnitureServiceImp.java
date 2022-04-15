package com.furniture.furnitureService.ServiceImp;

import com.furniture.furnitureService.Model.BillDetails;
import com.furniture.furnitureService.Model.Furniture;
import com.furniture.furnitureService.Repository.FurnitureRepository;
import com.furniture.furnitureService.Service.FurnitureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
public class FurnitureServiceImp implements FurnitureService {

    @Autowired
    private FurnitureRepository furnitureRepository;

    @Override
    public boolean putOnSale(Integer id) {
        Furniture tmp = furnitureRepository.findById(id).orElse(null);
        if(tmp!=null){
            tmp.setStatus(1);
            furnitureRepository.save(tmp);
            return true;
        }
        return false;
    }
}
