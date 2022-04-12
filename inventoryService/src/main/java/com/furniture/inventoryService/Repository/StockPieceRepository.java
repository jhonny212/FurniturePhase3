package com.furniture.inventoryService.Repository;

import com.furniture.inventoryService.Model.StockPiece;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StockPieceRepository extends JpaRepository<StockPiece,Integer> {
    List<StockPiece> findAllByPiece_IdAndStatus(Integer id, Integer status);
}
