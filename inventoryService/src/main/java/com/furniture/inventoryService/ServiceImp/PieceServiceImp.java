package com.furniture.inventoryService.ServiceImp;

import com.furniture.inventoryService.Model.Piece;
import com.furniture.inventoryService.Repository.PieceRepository;
import com.furniture.inventoryService.Service.PieceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PieceServiceImp implements PieceService{

    @Autowired
    private PieceRepository pieceRepository;

    @Override
    public Piece createPiece(Piece piece) {
        try{
            return pieceRepository.save(piece);
        }catch(Exception e){
            return null;
        }
    }

    @Override
    public Piece updatePiece(Piece piece) {
        return createPiece(piece);
    }

    @Override
    public Piece getPieceById(Integer id) {
        return pieceRepository.findById(id).orElse(null);
    }
}
