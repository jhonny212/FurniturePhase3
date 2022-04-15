package com.furniture.furnitureService.Service;

import com.furniture.furnitureService.Model.BillDetails;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;

public interface FurnitureService {
    public boolean putOnSale(Integer id);
}
