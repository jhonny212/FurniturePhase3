package com.furniture.reportService.Controller;

import com.furniture.reportService.ServiceImp.FurnitureServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/admin/report")
public class FurnitureReportController {
    @Autowired
    FurnitureServiceImp furnitureServiceImp;
    @GetMapping("/devolution")
    public ResponseEntity getDevolutions(@RequestParam Optional<String> date1,
                                         @RequestParam Optional<String> date2){
        return ResponseEntity.ok().body(furnitureServiceImp.getLost(date1,date2));
    }

}
