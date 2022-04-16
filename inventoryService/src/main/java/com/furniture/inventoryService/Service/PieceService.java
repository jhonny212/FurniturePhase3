package com.furniture.inventoryService.Service;

import com.furniture.inventoryService.Model.Piece;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface PieceService {
    public Piece createPiece(Piece piece,boolean bool);
    public Piece updatePiece(Piece piece);
    public Piece getPieceById(Integer id);
    public boolean addInStock(Piece piece,int stock,double cost);
    public boolean addInStock(int id,int stock,double cost);
    public boolean removeInStock(int id, int amount);
    public ResponseEntity<Page<Piece>> getAllPieces(Optional<Integer> pageNumber, Optional<String> name);
    public ResponseEntity<String> deletePiece(Integer id);
}
