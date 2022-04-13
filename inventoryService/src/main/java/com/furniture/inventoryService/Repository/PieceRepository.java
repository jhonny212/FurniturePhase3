package com.furniture.inventoryService.Repository;

import com.furniture.inventoryService.Model.Piece;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PieceRepository extends JpaRepository<Piece,Integer> {
}
