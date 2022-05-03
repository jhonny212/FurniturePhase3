package com.furniture.saleService.ServiceImp;

import com.furniture.saleService.Model.Furniture;
import com.furniture.saleService.Model.FurnitureInBill;
import com.furniture.saleService.Model.Profile;
import com.furniture.saleService.config.JWTAuthorizationFilter;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface FurnitureInBillService {

    public ResponseEntity<List<FurnitureInBill>> getFurnituresInBillBySession(String token, JWTAuthorizationFilter jwt);
    public ResponseEntity<Boolean> removeFurnitureFromBill(String token, Integer code,JWTAuthorizationFilter jwt);
    public ResponseEntity<Boolean> addFurnitureToBill(Furniture furniture, String token,JWTAuthorizationFilter jwt);
    public ResponseEntity<Boolean> deleteAllFurnituresInBillFromSession(String token,JWTAuthorizationFilter jwt);

}
