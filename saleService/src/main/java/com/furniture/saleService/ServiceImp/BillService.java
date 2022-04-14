package com.furniture.saleService.ServiceImp;

import com.furniture.saleService.Model.Client;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface BillService {
    public List<Object[]> getDetailBill(int id);
    public Object getClientByIdBill(int id);
    public ResponseEntity<Object> getBestEarner(String initialDate, String finalDate);
    public ResponseEntity<Object> getBestSeller(String initialDate, String finalDate);
}
