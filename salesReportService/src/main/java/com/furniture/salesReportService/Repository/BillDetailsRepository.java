package com.furniture.salesReportService.Repository;

import com.furniture.salesReportService.Model.Bill;
import com.furniture.salesReportService.Model.BillDetails;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;

public interface BillDetailsRepository extends JpaRepository<BillDetails, Integer> {

    @Query(value = "SELECT \n" +
            "\tSUM(billd.price_sale) AS sales, \n" +
            "\tSUM(billd.cost_lost) AS lost, \n" +
            "\tSUM(f.cost) AS costs,\n" +
            "\t(SUM(billd.price_sale) - SUM(f.cost) - SUM(billd.cost_lost)) AS earnings \n" +
            "\tFROM \"bill_details\" AS billd \n" +
            "\tLEFT JOIN \"bill\" AS bill \n" +
            "\tON billd.id_bill=bill.id_bill \n" +
            "\tLEFT JOIN \"furniture\" AS f ON f.code=billd.id_furniture\n" +
            "\tWHERE" +
            "\tbill.datetime BETWEEN ? AND ?;",nativeQuery = true)
    Object findEarnings(Date date1, Date date2);

    Page<BillDetails> findAllByBill(Bill id_bill, Pageable pageable);
    Page<BillDetails> findAllByBill_Client_Id(Integer nit, Pageable pageable);
    Page<BillDetails> findAllByBill_Client_IdAndDateReturnNull(Integer nit, Pageable pageable);
    Page<BillDetails> findAllByBill_Client_IdAndDateReturnNotNull(Integer nit, Pageable pageable);
    Page<BillDetails> findAllByBill_Client_IdAndDateReturnNotNullAndDateReturnBetween(Integer nit, Date date1, Date date2, Pageable pageable);
    Page<BillDetails> findAllByBill_DateTime(Date date,Pageable pageable);
}