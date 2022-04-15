package com.furniture.reportService.Repository;

import com.furniture.reportService.Model.BillDetails;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

public interface BillDetailsRepository extends JpaRepository<BillDetails, Integer> {

    Page<BillDetails> findAllByBill_DateTimeBetweenAndDateReturnIsNull(Date date1, Date date2, Pageable pageable);
    Page<BillDetails> findAllByBill_DateTimeBetween(Date date1, Date date2, Pageable pageable);
}