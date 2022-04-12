package com.furniture.saleService.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("sale/furniture")
public class FurnitureController {

    @PostMapping("/devolution")
    public ResponseEntity returnFurniture(
            @RequestParam(value = "id") int id,
            @RequestParam(value = "code") int code
    ){
        return null;
    }
}
