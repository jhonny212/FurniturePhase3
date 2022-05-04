package com.furniture.inventoryService.ServiceImp;

import com.furniture.inventoryService.Model.Piece;
import com.furniture.inventoryService.Model.StockPiece;
import com.furniture.inventoryService.Repository.PieceRepository;
import com.furniture.inventoryService.Repository.StockPieceRepository;
import com.furniture.inventoryService.Service.PieceService;

import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@EnableCaching
public class PieceServiceImp implements PieceService{

    @Autowired
    private PieceRepository pieceRepository;
    @Autowired
    private StockPieceRepository stockPieceRepository;

    @Override
    public Piece createPiece(Piece piece,boolean bool) {
        try{
            Piece tmp= pieceRepository.save(piece);
            if(bool){
                boolean exit = addInStock(tmp,piece.getStock(),piece.cost);
                if(!exit){
                    tmp.setStock(0);
                    pieceRepository.save(tmp);
                }
            }
            return tmp;
        }catch(Exception e){
            return null;
        }
    }

    @Override
    public Piece updatePiece(Piece piece) {
        return createPiece(piece,false);
    }

    @Override
    @Cacheable(key = "#id",value = "piece")
    public Piece getPieceById(Integer id) {
        return pieceRepository.findById(id).orElse(null);
    }

    @Override
    public boolean addInStock(Piece piece,int stock,double cost) {
        if(stock<0 || cost<0){
            return false;
        }
        List<StockPiece> pieces = new ArrayList<>();
        for (int i = 0; i < stock; i++) {
            StockPiece t = new StockPiece();
            t.setStatus(0);
            t.setCost(cost);
            t.setPiece(piece);
            pieces.add(t);
        }
        this.stockPieceRepository.saveAll(pieces);
        return true;
    }

    @Override
    public boolean addInStock(int id, int stock,double cost) {
        Optional<Piece> tmp = pieceRepository.findById(id);
        boolean isadd = tmp.filter(piece -> addInStock(piece, stock, cost)).isPresent();
        if(isadd){
            tmp.get().setStock(tmp.get().getStock()+stock);
            this.pieceRepository.save(tmp.get());
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean removeInStock(int id, int amount){
        Piece tmp = getPieceById(id);
        if(tmp==null || amount<0){
            return false;
        }
        if(tmp.getStock()>= amount){
            tmp.setStock(tmp.getStock()-amount);
            List<StockPiece> pieces = this.stockPieceRepository.findAllByPiece_IdAndStatus(tmp.getId(),0);
            List<StockPiece> delPieces = new ArrayList<>();
            for (int i = 0; i < amount; i++){
                delPieces.add(pieces.get(i));
            }
            pieceRepository.save(tmp);
            stockPieceRepository.deleteAll(delPieces);
            return true;
        }
        return false;
    }

    @Override
    public ResponseEntity<Page<Piece>> getAllPieces(Optional<Integer> pageNumber, Optional<String> name) {
        return ResponseEntity.status(HttpStatus.OK).body(this.pieceRepository.findByNameContainsIgnoreCase(
                name.orElse(""),
                PageRequest.of(
                        pageNumber.orElse(0),5
                )
        ));
    }

    @Override
    public ResponseEntity<String> deletePiece(Integer id){
        this.pieceRepository.deleteById(id);
        if(this.pieceRepository.existsById(id)){
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("No se eliminó la pieza correctamente");
        }else{
            return ResponseEntity.status(HttpStatus.OK).body("Se ha eliminado la pieza correctamente");
        }
    }
}
