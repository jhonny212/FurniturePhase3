package com.furniture.saleService.ServiceImp;

import com.furniture.saleService.Model.Furniture;
import com.furniture.saleService.Model.OnSaleData;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface FurnitureService {
    public boolean returnFurniture(int id,int code);
    public ResponseEntity<Boolean> putFurnitureOnSale(OnSaleData onSaleData);
    public ResponseEntity<Page<Furniture>> getFurnituresOnSale(Optional<Integer> page, Optional<String> name);
    public ResponseEntity<Page<Furniture>> getFurnituresOnStorage(Optional<Integer> page,Optional<String> name);
}
