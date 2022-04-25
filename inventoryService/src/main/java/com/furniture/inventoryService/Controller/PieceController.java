package com.furniture.inventoryService.Controller;

import javax.validation.Valid;

import com.furniture.inventoryService.Model.Piece;
import com.furniture.inventoryService.ServiceImp.PieceServiceImp;
import com.furniture.inventoryService.Util.Util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController()
@RequestMapping("fabricate/piece")
public class PieceController {
    @Autowired
    private Util util;
    @Autowired
    private PieceServiceImp PieceServiceImp;

    @PostMapping
    public ResponseEntity<Piece> createPiece(
        @Valid @RequestBody Piece piece, BindingResult result,@RequestHeader("Authorization") String auth) {
        if(result.hasErrors()){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,util.formatMessage(result));
        }
        Piece tmp = PieceServiceImp.createPiece(piece,true);
        if(tmp==null){
            return ResponseEntity.internalServerError().body(null);
        }
        return new ResponseEntity<>(tmp,HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Piece> updatePiece(
        @Valid @RequestBody Piece piece,BindingResult result,@RequestHeader("Authorization") String auth
    ){
        if(result.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,util.formatMessage(result));
        }
        Piece tmp = PieceServiceImp.updatePiece(piece);
        if(tmp==null){
            return ResponseEntity.internalServerError().body(null);
        }
        return new ResponseEntity<>(tmp,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Piece> getPieceById(@PathVariable("id") int id,@RequestHeader("Authorization") String auth){
        Piece piece = PieceServiceImp.getPieceById(id);
        if(piece == null){
            return new ResponseEntity<>(null,HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(piece,HttpStatus.OK);
    }

    @PostMapping("/stock/{id}/{stock}/{cost}")
    public ResponseEntity<Boolean> addInStock(
        @PathVariable("id") int id, @PathVariable("stock") int stock,
        @PathVariable("cost") double cost,@RequestHeader("Authorization") String auth
        ){
        if(this.PieceServiceImp.addInStock(id,stock,cost)){
            return ResponseEntity.ok().body(true);
        }
        return ResponseEntity.badRequest().body(false);
    }

    @PostMapping("/remove/{id}/{stock}")
    public ResponseEntity<Boolean> removeInStokc(
        @PathVariable("id") int id,
        @PathVariable("stock") int stock,@RequestHeader("Authorization") String auth
        ){
        boolean bool = this.PieceServiceImp.removeInStock(id,stock);
        if(bool){
            return ResponseEntity.ok().body(true);
        }
        return ResponseEntity.badRequest().body(false);
    }

    @GetMapping("/get-all")
    public ResponseEntity<Page<Piece>> getAllPieces(@RequestParam Optional<Integer> page, @RequestParam Optional<String> name){
        return this.PieceServiceImp.getAllPieces(page,name);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePiece(@PathVariable("id") Integer id){
        return this.PieceServiceImp.deletePiece(id);
    }
}
