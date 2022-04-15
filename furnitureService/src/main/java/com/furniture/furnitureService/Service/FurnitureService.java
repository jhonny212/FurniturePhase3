package com.furniture.furnitureService.Service;

import com.furniture.furnitureService.Model.Furniture;
import org.springframework.data.domain.Page;

import java.text.ParseException;
import java.util.Date;
import java.util.Optional;
import com.furniture.furnitureService.Model.BillDetails;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;

public interface FurnitureService {
    public boolean putOnSale(Integer id);

    public Page<Furniture> getAllFurniture(Optional<String> filter, Optional<Integer> page);
    public Page<Furniture> getAllFurniture(Optional<Integer> page);
    public Page<Furniture> getAllFurnitureFilter(Optional<String> date1, Optional<String> date2, Optional<Integer> sort, Optional<Integer> page) throws ParseException;
    public Furniture getFurniture(Integer id);
}
