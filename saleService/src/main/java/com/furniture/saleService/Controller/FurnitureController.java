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

    @PostMapping("/devolution")
    public ResponseEntity returnFurniture(
            @RequestParam(value = "id") int id,
            @RequestParam(value = "code") int code
    ){
        return null;
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
