package com.furniture.salesReportService.Repository;

import com.furniture.salesReportService.Model.Bill;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

public interface BillRepository extends JpaRepository<Bill, Integer> {

    Page<Bill> findBillByClientId(Integer nit, Pageable page);
    Page<Bill> findByClient_NameContainsAndDateTimeBetween(String nit, Date date1, Date date2, Pageable page);
}