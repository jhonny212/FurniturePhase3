package com.furniture.saleService.Controller;

import com.furniture.saleService.Model.Furniture;
import com.furniture.saleService.Model.FurnitureInBill;
import com.furniture.saleService.Service.FurnitureInBillServiceImp;
import com.furniture.saleService.Service.FurnitureServiceImp;
import com.furniture.saleService.config.JWTAuthorizationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sales/furniture-in-bill")
public class FurnitureInBillController {

    @Autowired
    private FurnitureInBillServiceImp furnitureInBillServiceImp;

    @GetMapping("/on-session")
    public ResponseEntity<List<FurnitureInBill>> getFurnituresInBill(@RequestHeader("Authorization") String token){
        return this.furnitureInBillServiceImp.getFurnituresInBillBySession(token, new JWTAuthorizationFilter());
    }

    @DeleteMapping("/on-session/{code}")
    public ResponseEntity<Boolean> removeFurnitureInBill(@PathVariable Integer code, @RequestHeader("Authorization") String token){
        return this.furnitureInBillServiceImp.removeFurnitureFromBill(token, code, new JWTAuthorizationFilter());
    }

    @DeleteMapping("/on-session")
    public ResponseEntity<Boolean> removeAllFurnituresInBill(@RequestHeader("Authorization") String token){
        return this.furnitureInBillServiceImp.deleteAllFurnituresInBillFromSession(token, new JWTAuthorizationFilter());
    }

    @PostMapping("/on-session")
    public ResponseEntity<Boolean> addFurnitureInBill(@RequestBody Furniture furniture, @RequestHeader("Authorization") String token){
        return this.furnitureInBillServiceImp.addFurnitureToBill(furniture, token, new JWTAuthorizationFilter());
    }

    @GetMapping("/is-on-session/{code}")
    public ResponseEntity<Boolean> isFurnitureOnSession(@PathVariable Integer code, @RequestHeader("Authorization") String token){
        return this.furnitureInBillServiceImp.isFurnitureOnSession(code, token, new JWTAuthorizationFilter());
    }
}
