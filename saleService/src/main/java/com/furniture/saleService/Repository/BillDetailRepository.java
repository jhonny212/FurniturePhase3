package com.furniture.saleService.Repository;

import com.furniture.saleService.Model.BillDetails;
import com.furniture.saleService.Model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface BillDetailRepository extends JpaRepository<BillDetails,Integer> {

    //client by id
    @Query(value = "select cl.name,cl.nit,cl.address from client as cl\n" +
            "inner join bill as b\n" +
            "on b.nit = cl.nit\n" +
            "where b.id_bill = ?;",nativeQuery = true)
    Object findClient(int id);

    @Query(value = "select bd.id_furniture,bd.price_sale,\n" +
            " f.name, bd.id, b.nit \n" +
            "from bill_details as bd inner join bill as b\n" +
            "on bd.id_bill = b.id_bill \n" +
            "inner join furniture as f\n" +
            "on bd.id_furniture = f.code\n" +
            "where b.id_bill = ? and bd.cost_lost = 0\n" +
            "",nativeQuery = true)
    List<Object[]> findAllDetailBils(int id);

    @Query(value = "SELECT b.datetime from bill_details  " +
            "inner join bill as b on bill_details.id_bill = b.id_bill " +
            "where bill_details.id = ? and " +
            "bill_details.id_furniture = ? " +
            ";", nativeQuery = true)
    Date getDetail(int id, int cod);
}
