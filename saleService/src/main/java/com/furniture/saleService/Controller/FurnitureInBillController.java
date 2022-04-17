package com.furniture.saleService.Controller;

import com.furniture.saleService.Model.Furniture;
import com.furniture.saleService.Model.FurnitureInBill;
import com.furniture.saleService.Service.FurnitureInBillServiceImp;
import com.furniture.saleService.Service.FurnitureServiceImp;
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
        return this.furnitureInBillServiceImp.getFurnituresInBillBySession(token);
    }

    @DeleteMapping("/on-session/{code}")
    public ResponseEntity<Boolean> removeFurnitureInBill(@PathVariable Integer code, @RequestHeader("Authorization") String token){
        return this.furnitureInBillServiceImp.removeFurnitureFromBill(token, code);
    }

    @DeleteMapping("/on-session")
    public ResponseEntity<Boolean> removeAllFurnituresInBill(@RequestHeader("Authorization") String token){
        return this.furnitureInBillServiceImp.deleteAllFurnituresInBillFromSession(token);
    }

    @PostMapping("/on-session")
    public ResponseEntity<Boolean> addFurnitureInBill(@RequestBody Furniture furniture, @RequestHeader("Authorization") String token){
        return this.addFurnitureInBill(furniture, token);
    }

}
