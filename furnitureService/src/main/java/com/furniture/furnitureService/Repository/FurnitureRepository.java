package com.furniture.furnitureService.Repository;

import com.furniture.furnitureService.Model.Furniture;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

public interface FurnitureRepository extends JpaRepository<Furniture,Integer> {

    Page<Furniture> findByNameContains(String name, Pageable page);
    Page<Furniture> findByCreationDateBetween(Date date1, Date date2, Pageable page);

}
