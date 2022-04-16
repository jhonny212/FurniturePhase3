package com.furniture.saleService.ServiceImp;

import com.furniture.saleService.Model.Client;
import org.springframework.http.ResponseEntity;

public interface ClientService {

    public ResponseEntity<Client> getClientByNit(Integer nit);
    public ResponseEntity<Boolean> createClientIfNotExist(Client client);
}
