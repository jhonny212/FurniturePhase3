package com.furniture.saleService.Repository;

import com.furniture.saleService.Model.Bill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillRepository extends JpaRepository<Bill, Integer> {
}
