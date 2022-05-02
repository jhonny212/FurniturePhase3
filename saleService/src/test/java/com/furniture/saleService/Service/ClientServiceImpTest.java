package com.furniture.saleService.Service;

import com.furniture.saleService.Model.Client;
import com.furniture.saleService.Repository.ClientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClientServiceImpTest {

    @Mock
    private ClientRepository clientRepository;
    @InjectMocks
    private ClientServiceImp clientServiceImp;

    @BeforeEach
    void setUp() { MockitoAnnotations.openMocks(this); }

    @Test
    public void createClientIfNotExistButExist(){
        Client client = new Client(1,"anyName","anyAddress");
        Mockito.when(
                clientRepository.existsById(Mockito.anyInt())
        ).thenReturn(true);
        assertEquals(
                HttpStatus.OK,
                clientServiceImp.createClientIfNotExist(client).getStatusCode()
        );
    }

    @Test
    public void createClientIfNotExistButNotExist(){
        Client client = new Client(1,"anyName","anyAddress");
        Mockito.when(
                clientRepository.existsById(Mockito.anyInt())
        ).thenReturn(false);
        Mockito.when(
                clientRepository.save(Mockito.any(Client.class))
        ).thenReturn(client);
        assertEquals(
                HttpStatus.OK,
                clientServiceImp.createClientIfNotExist(client).getStatusCode()
        );
    }

    @Test
    public void createClientIfNotExistsButThrowError(){
        Client client = new Client(1,"anyName","anyAddress");
        Mockito.when(
                clientRepository.existsById(Mockito.anyInt())
        ).thenReturn(false);
        Mockito.when(
                clientRepository.save(Mockito.any(Client.class))
        ).thenThrow(RuntimeException.class);
        assertEquals(
                HttpStatus.INTERNAL_SERVER_ERROR,
                clientServiceImp.createClientIfNotExist(client).getStatusCode()
        );
    }
}
