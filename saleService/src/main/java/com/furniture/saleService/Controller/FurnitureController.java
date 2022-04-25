package com.furniture.saleService.Controller;

import com.furniture.saleService.Model.Furniture;
import com.furniture.saleService.Model.OnSaleData;
import com.furniture.saleService.Service.FurnitureServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("sale/furniture")
public class FurnitureController {

    @Autowired
    private FurnitureServiceImp furnitureServiceImp;

    @PostMapping("/devolution/{id}/{code}")
    public ResponseEntity returnFurniture(
            @PathVariable(value = "id") int id,
            @PathVariable(value = "code") int code,
            @RequestHeader("Authorization") String auth
    ){
        boolean valid = furnitureServiceImp.returnFurniture(id, code);
        if(!valid){
            return ResponseEntity.badRequest().body(false);
        }
        return ResponseEntity.ok(true);
    }

    @PutMapping("/put-furniture-on-sale")
    public ResponseEntity<Boolean> putFurnitureOnSale(@RequestBody OnSaleData onSaleData){
        return this.furnitureServiceImp.putFurnitureOnSale(onSaleData);
    }

    @GetMapping("/on-sale")
    public ResponseEntity<Page<Furniture>> getFurnituresOnSale(@RequestParam Optional<Integer> page, @RequestParam Optional<String> name){
        return this.furnitureServiceImp.getFurnituresOnSale(page, name);
    }

    @GetMapping("/on-storage")
    public ResponseEntity<Page<Furniture>> getFurnituresOnStorage(@RequestParam Optional<Integer> page, @RequestParam Optional<String> name){
        return this.furnitureServiceImp.getFurnituresOnStorage(page, name);
    }
}
