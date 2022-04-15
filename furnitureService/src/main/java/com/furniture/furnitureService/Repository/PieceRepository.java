package com.furniture.furnitureService.Repository;

import com.furniture.furnitureService.Model.Piece;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PieceRepository extends JpaRepository<Piece, Integer> {

    public Page<Piece> findByNameContainsIgnoreCase(String name, Pageable page);

}