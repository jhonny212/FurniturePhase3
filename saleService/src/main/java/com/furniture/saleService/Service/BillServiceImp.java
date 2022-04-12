package com.furniture.saleService.Service;

import com.furniture.saleService.Model.Client;
import com.furniture.saleService.Repository.BillDetailRepository;
import com.furniture.saleService.ServiceImp.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillServiceImp implements BillService {
    @Autowired
    private BillDetailRepository billDetailRepository;

    @Override
    public List<Object[]> getDetailBill(int id) {
        try{
            return billDetailRepository.findAllDetailBils(id);
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public Object getClientByIdBill(int id) {
        try{
            return billDetailRepository.findClient(id);
        }catch (Exception e){
            return null;
        }
    }
}
