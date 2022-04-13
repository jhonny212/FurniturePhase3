package com.furniture.saleService.Repository;

import com.furniture.saleService.Model.Furniture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FurnitureRepository extends JpaRepository<Furniture,Integer> {
}
