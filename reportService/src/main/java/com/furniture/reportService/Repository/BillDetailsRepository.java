package com.furniture.reportService.Repository;

import com.furniture.reportService.Model.BillDetails;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface BillDetailsRepository extends JpaRepository<BillDetails, Integer> {

    Page<BillDetails> findAllByBill_DateTimeBetweenAndDateReturnIsNull(Date date1, Date date2, Pageable pageable);
    Page<BillDetails> findAllByBill_DateTimeBetween(Date date1, Date date2, Pageable pageable);
    @Query(value = "select f.\"name\",bd.cost_lost,\n" +
            "bd.date_return,bd.price_sale,b.nit,pf.username\n" +
            "from public.bill_details as bd\n" +
            "inner join public.bill as b ON b.id_bill = bd.id_bill\n" +
            "inner join public.profile as pf ON pf.id_user = b.id_user\n" +
            "inner join public.furniture as f on f.code = bd.id_furniture\n" +
            "where bd.cost_lost > ?;",nativeQuery = true)
    List<Object[]> getLost1(double lost);

    @Query(value = "select f.\"name\",bd.cost_lost,\n" +
            "bd.date_return,bd.price_sale,b.nit,pf.username\n" +
            "from public.bill_details as bd\n" +
            "inner join public.bill as b ON b.id_bill = bd.id_bill\n" +
            "inner join public.profile as pf ON pf.id_user = b.id_user\n" +
            "inner join public.furniture as f on f.code = bd.id_furniture\n" +
            "where bd.cost_lost > ? and bd.date_return BETWEEN ? and ?;",nativeQuery = true)
    List<Object[]> getLost2(double lost, Date d1, Date d2);
}