package com.furniture.saleService.Controller;

import com.furniture.saleService.Model.Client;
import com.furniture.saleService.Service.ClientServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sales/client")
public class ClientController {

    @Autowired
    private ClientServiceImp clientServiceImp;

    @GetMapping("/{nit}")
    public ResponseEntity<Client> getClient(@PathVariable Integer nit){
        return this.clientServiceImp.getClientByNit(nit);
    }

}
