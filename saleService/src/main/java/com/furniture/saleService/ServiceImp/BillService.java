package com.furniture.saleService.ServiceImp;

import com.furniture.saleService.Model.Client;

import java.util.List;

public interface BillService {
    public List<Object[]> getDetailBill(int id);
    public Object getClientByIdBill(int id);
}
