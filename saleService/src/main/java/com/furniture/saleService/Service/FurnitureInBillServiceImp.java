package com.furniture.saleService.Service;

import com.furniture.saleService.Model.Furniture;
import com.furniture.saleService.Model.FurnitureInBill;
import com.furniture.saleService.Model.Profile;
import com.furniture.saleService.Repository.FurnitureInBillRepository;
import com.furniture.saleService.ServiceImp.FurnitureInBillService;
import com.furniture.saleService.config.JWTAuthorizationFilter;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FurnitureInBillServiceImp implements FurnitureInBillService {

    @Autowired
    private FurnitureInBillRepository furnitureInBillRepository;

    @Override
    public ResponseEntity<List<FurnitureInBill>> getFurnituresInBillBySession(String token,JWTAuthorizationFilter jwt) {
        Profile profile = jwt.getProfileFromToken(token);
        return ResponseEntity.status(HttpStatus.OK).body(this.furnitureInBillRepository.findByProfile(profile));
    }

    @Override
    public ResponseEntity<Boolean> removeFurnitureFromBill(String token, Integer code,JWTAuthorizationFilter jwt) {
        Profile profile = jwt.getProfileFromToken(token);
        FurnitureInBill fib = this.furnitureInBillRepository.findByFurnitureCodeAndProfile(code,profile);
        this.furnitureInBillRepository.delete(fib);
        return ResponseEntity.status(HttpStatus.OK).body(!this.furnitureInBillRepository.existsById(code));
    }

    @Override
    public ResponseEntity<Boolean> addFurnitureToBill(Furniture furniture, String token, JWTAuthorizationFilter jwt) {
        Profile profile = jwt.getProfileFromToken(token);
        FurnitureInBill furnitureInBill = new FurnitureInBill(furniture, profile);
        this.furnitureInBillRepository.save(furnitureInBill);
        return ResponseEntity.status(HttpStatus.OK).body(this.furnitureInBillRepository.existsById(furnitureInBill.getId()));
    }

    @Override
    public ResponseEntity<Boolean> deleteAllFurnituresInBillFromSession(String token,JWTAuthorizationFilter jwt) {
        Profile profile = jwt.getProfileFromToken(token);
        this.furnitureInBillRepository.deleteFurnitureInBillByProfile(profile);
        return ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @Override
    public ResponseEntity<Boolean> isFurnitureOnSession(Integer code, String token,JWTAuthorizationFilter jwt) {
        Profile profile = jwt.getProfileFromToken(token);
        return ResponseEntity.status(HttpStatus.OK).body(
                !this.furnitureInBillRepository.existsByFurnitureCodeAndProfile(code, profile)
        );
    }
}
