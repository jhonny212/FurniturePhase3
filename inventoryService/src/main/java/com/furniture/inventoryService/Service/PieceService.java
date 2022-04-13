package com.furniture.inventoryService.Service;

import com.furniture.inventoryService.Model.Piece;

public interface PieceService {
    public Piece createPiece(Piece piece,boolean bool);
    public Piece updatePiece(Piece piece);
    public Piece getPieceById(Integer id);
    public boolean addInStock(Piece piece,int stock,double cost);
    public boolean addInStock(int id,int stock,double cost);
    public boolean removeInStock(int id, int amount);
}
