package com.furniture.reportService.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/report")
public class FurnitureReportController {

    @GetMapping("/devolution")
    public ResponseEntity getDevolutions(){
        return null;
    }

}
