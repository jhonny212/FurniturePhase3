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

    @Query(value="SELECT b.id_user, " +
            "SUM(bd.price_sale - (bd.cost_lost + f.cost)) AS sales, " +
            "p.first_name AS name, " +
            "p.last_name AS surname " +
            "FROM bill b RIGHT JOIN bill_details bd ON b.id_bill = bd.id_bill " +
            "JOIN profile p ON p.id_user = b.id_user " +
            "JOIN furniture f ON f.code = bd.id_furniture " +
            "WHERE b.dateTime BETWEEN ? AND ? " +
            "GROUP BY b.id_user, p.first_name, p.last_name " +
            "ORDER BY sales DESC;", nativeQuery = true)
    List<Object[]> getBestEarner(Date d1, Date d2);

    @Query(value="SELECT b.id_user, " +
            "COUNT(bd.id) AS sales, " +
            "p.first_name AS name, " +
            "p.last_name AS surname " +
            "FROM bill b RIGHT JOIN bill_details bd ON b.id_bill = bd.id_bill " +
            "JOIN profile p ON p.id_user = b.id_user " +
            "WHERE b.dateTime BETWEEN ? AND ? " +
            "GROUP BY b.id_user, p.first_name, p.last_name " +
            "ORDER BY sales DESC;",nativeQuery = true)
    List<Object[]> getBestSeller(Date d1, Date d2);

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
    List<Object[]> getLost2(double lost,Date d1,Date d2);
}
