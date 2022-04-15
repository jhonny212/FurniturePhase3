package com.furniture.saleService.Service;

import com.furniture.saleService.Model.Client;
import com.furniture.saleService.Repository.ClientRepository;
import com.furniture.saleService.ServiceImp.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ClientServiceImp implements ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public ResponseEntity<Client> getClientByNit(Integer nit) {
        Client client = this.clientRepository.findById(nit).orElse(null);
        if(client == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        return ResponseEntity.status(HttpStatus.OK).body(client);
    }

    @Override
    public ResponseEntity<Boolean> createClientIfNotExist(Client client){
        if(this.clientRepository.existsById(client.getId())){
            return ResponseEntity.status(HttpStatus.OK).body(true);
        }else{
            try{
                this.clientRepository.save(client);
                return ResponseEntity.status(HttpStatus.OK).body(false);
            }catch(Exception ex){
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
            }
        }
    }
}
