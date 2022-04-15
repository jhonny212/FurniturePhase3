package com.furniture.furnitureService.Controller;

import com.furniture.furnitureService.Model.Furniture;
import com.furniture.furnitureService.Model.BillDetails;
import com.furniture.furnitureService.ServiceImp.FurnitureServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.ArrayList;

@RestController
@RequestMapping("/fabricate/furniture")
public class FurnitureController {

    @Autowired
    private FurnitureServiceImp furnitureServiceImp;

    @PutMapping("/{id}")
    public ResponseEntity<Boolean> onSale(@PathVariable("id") int id){
        return ResponseEntity.ok().body(furnitureServiceImp.putOnSale(id));
    }

    @GetMapping("/get-allFurniture")
    public ResponseEntity<Page<Furniture>> getAllFuniture(
            @RequestParam Optional<String> filter,
            @RequestParam Optional<Integer> page
    ){
        return ResponseEntity.ok().body(furnitureServiceImp.getAllFurniture(filter, page));
    }

    @GetMapping("/get-allFurniture-filter")
    public ResponseEntity<Page<Furniture>> getAllFunitureFilter(
            @RequestParam Optional<String> date1,
            @RequestParam Optional<String> date2,
            @RequestParam Optional<Integer> sort,
            @RequestParam Optional<Integer> page
    ) throws ParseException {
        return ResponseEntity.ok().body(this.furnitureServiceImp.getAllFurnitureFilter(date1,date2,sort, page));
    }

    @GetMapping("/get-furniture")
    public ResponseEntity<Furniture> getFurniture(
            @RequestParam Optional<Integer> code
    ){
        if(this.furnitureServiceImp.isExisteFurniture(code.get())){
            return ResponseEntity.ok().body(furnitureServiceImp.getFurniture(code.get()));
        }
        return ResponseEntity.badRequest().body(new Furniture("No se encontro el Mueble"));
    }
}
