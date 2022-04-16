package com.furniture.reportService.Repository;

import com.furniture.reportService.Model.Furniture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface FurnitureRepository extends JpaRepository<Furniture, Integer> {

    //REPORTS
    @Query(value = "SELECT f.*, (COUNT(billd.id_furniture)) AS amount FROM \n" +
            "\"furniture\" AS F LEFT JOIN \"bill_details\" AS billd ON f.code=billd.id_furniture INNER JOIN \"bill\" AS bill \n" +
            "ON billd.id_bill=bill.id_bill \n" +
            "WHERE f.code=billd.id_furniture AND bill.datetime between ? and ? \n" +
            "GROUP BY billd.id_furniture, f.code ORDER BY amount desc LIMIT 10;", nativeQuery = true)
    List<Furniture> findMaxFurniture(Date date1, Date date2);

    @Query(value = "SELECT (COUNT(billd.id_furniture)) AS amount FROM \n" +
            "\"furniture\" AS F LEFT JOIN \"bill_details\" AS billd ON f.code=billd.id_furniture INNER JOIN \"bill\" AS bill \n" +
            "ON billd.id_bill=bill.id_bill \n" +
            "WHERE f.code=billd.id_furniture AND bill.datetime between ? and ? \n" +
            "GROUP BY billd.id_furniture, f.code ORDER BY amount desc LIMIT 10;", nativeQuery = true)
    List<Double> findMaxFurnitureNum(Date date1, Date date2);

    @Query(value = "SELECT f.*, (COUNT(billd.id_furniture)) AS amount FROM \n" +
            "\"furniture\" AS F LEFT JOIN \"bill_details\" AS billd ON f.code=billd.id_furniture INNER JOIN \"bill\" AS bill \n" +
            "ON billd.id_bill=bill.id_bill \n" +
            "WHERE f.code=billd.id_furniture AND bill.datetime between ? and ? \n" +
            "GROUP BY billd.id_furniture, f.code ORDER BY amount asc LIMIT 10;", nativeQuery = true)
    List<Furniture> findMinFurniture(Date date1, Date date2);

    @Query(value = "SELECT (COUNT(billd.id_furniture)) AS amount FROM \n" +
            "\"furniture\" AS F LEFT JOIN \"bill_details\" AS billd ON f.code=billd.id_furniture INNER JOIN \"bill\" AS bill \n" +
            "ON billd.id_bill=bill.id_bill \n" +
            "WHERE f.code=billd.id_furniture AND bill.datetime between ? and ? \n" +
            "GROUP BY billd.id_furniture, f.code ORDER BY amount asc LIMIT 10;", nativeQuery = true)
    List<Double> findMinFurnitureNum(Date date1, Date date2);
}