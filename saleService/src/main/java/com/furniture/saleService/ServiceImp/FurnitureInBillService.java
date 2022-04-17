package com.furniture.saleService.ServiceImp;

import com.furniture.saleService.Model.Furniture;
import com.furniture.saleService.Model.FurnitureInBill;
import com.furniture.saleService.Model.Profile;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface FurnitureInBillService {

    public ResponseEntity<List<FurnitureInBill>> getFurnituresInBillBySession(String token);
    public ResponseEntity<Boolean> removeFurnitureFromBill(String token, Integer code);
    public ResponseEntity<Boolean> addFurnitureToBill(FurnitureInBill furnitureInBill);
    public ResponseEntity<Boolean> deleteAllFurnituresInBillFromSession(String token);

}
