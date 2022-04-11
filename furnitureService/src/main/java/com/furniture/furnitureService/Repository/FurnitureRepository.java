package com.furniture.furnitureService.Repository;

import com.furniture.furnitureService.Model.Furniture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FurnitureRepository extends JpaRepository<Furniture,Integer> {
}
