package com.furniture.furnitureService.ServiceImp;

import com.furniture.furnitureService.Model.Furniture;
import com.furniture.furnitureService.Repository.FurnitureRepository;
import com.furniture.furnitureService.Service.FurnitureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
