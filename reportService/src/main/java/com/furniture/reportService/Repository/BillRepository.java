package com.furniture.reportService.Repository;

import com.furniture.reportService.Model.Bill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillRepository extends JpaRepository<Bill, Integer> {
}