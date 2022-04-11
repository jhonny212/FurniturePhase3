package com.furniture.inventoryService.Service;

import com.furniture.inventoryService.Model.Piece;

public interface PieceService {
    public Piece createPiece(Piece piece);
    public Piece updatePiece(Piece piece);
    public Piece getPieceById(Integer id);
}
