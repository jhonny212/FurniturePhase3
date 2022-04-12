package com.furniture.inventoryService.Controller;

import javax.validation.Valid;

import com.furniture.inventoryService.Model.Piece;
import com.furniture.inventoryService.ServiceImp.PieceServiceImp;
import com.furniture.inventoryService.Util.Util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController()
@RequestMapping("fabricate/piece")
public class PieceController {
    @Autowired
    private Util util;
    @Autowired
    private PieceServiceImp PieceServiceImp;

    @PostMapping
    public ResponseEntity<Piece> createPiece(
        @Valid @RequestBody Piece piece, BindingResult result) {
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
        @Valid @RequestBody Piece piece,BindingResult result
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
    public ResponseEntity<Piece> getPieceById(@PathVariable("id") int id){
        Piece piece = PieceServiceImp.getPieceById(id);
        if(piece == null){
            return new ResponseEntity<>(null,HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(piece,HttpStatus.OK);
    }

    @PostMapping("/stock/{id}/{stock}/{cost}")
    public ResponseEntity<Boolean> addInStock(
        @PathVariable("id") int id, @PathVariable("stock") int stock,
        @PathVariable("cost") double cost
        ){
        if(this.PieceServiceImp.addInStock(id,stock,cost)){
            return ResponseEntity.ok().body(true);
        }
        return ResponseEntity.badRequest().body(false);
    }

    @PostMapping("/remove/{id}/{stock}")
    public ResponseEntity<Boolean> removeInStokc(
        @PathVariable("id") int id,
        @PathVariable("stock") int stock
        ){
        boolean bool = this.PieceServiceImp.removeInStock(id,stock);
        if(bool){
            return ResponseEntity.ok().body(true);
        }
        return ResponseEntity.badRequest().body(false);
    }
}
