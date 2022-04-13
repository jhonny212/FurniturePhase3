package com.furniture.furnitureService.Controller;

import com.furniture.furnitureService.ServiceImp.FurnitureServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fabricate/furniture")
public class FurnitureController {
    @Autowired
    private FurnitureServiceImp furnitureServiceImp;

    @PutMapping("/{id}")
    public ResponseEntity<Boolean> onSale(@PathVariable("id") int id){
        return ResponseEntity.ok().body(furnitureServiceImp.putOnSale(id));
    }
}
