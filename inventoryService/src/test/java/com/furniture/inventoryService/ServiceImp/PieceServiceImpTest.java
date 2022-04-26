package com.furniture.inventoryService.ServiceImp;

import com.furniture.inventoryService.Model.Category;
import com.furniture.inventoryService.Model.StockPiece;
import com.furniture.inventoryService.Repository.StockPieceRepository;
import org.hibernate.exception.ConstraintViolationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.furniture.inventoryService.Model.Piece;
import com.furniture.inventoryService.Repository.PieceRepository;

class PieceServiceImpTest {
    @Mock
    private PieceRepository pieceRepository;
    @Mock
    private StockPieceRepository stockPieceRepository;
    @InjectMocks
    private PieceServiceImp pieceServiceImp;
    Piece piece;
    List<StockPiece> list;
    
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        piece = new Piece();
    }

    @Test
    void createPiece() {
        piece.setStock(0);
        Mockito.when(
            pieceRepository.save(Mockito.any(Piece.class))
        ).thenReturn(piece);
        Piece tmp = new Piece();
        tmp.setStock(0);
        Mockito.when(
                pieceRepository.save(Mockito.any(Piece.class))
        ).thenReturn(tmp);
        assertEquals(pieceServiceImp.createPiece(piece,true),tmp);
    }

    @Test
    void createPieceButNotAddInStock(){
        piece.setStock(0);
        piece.setStock(-1);
        piece.setCost(-1);
        Mockito.when(
                pieceRepository.save(Mockito.any(Piece.class))
        ).thenReturn(piece);
        assertEquals(pieceServiceImp.createPiece(piece,true),piece);
    }

    @Test
    void createPieceAndThrowException(){
        Mockito.when(
            pieceRepository.save(Mockito.any(Piece.class))
        ).thenThrow(ConstraintViolationException.class);
        assertNull(pieceServiceImp.createPiece(piece,true));
    }

    @Test
    void updatePiece() {
        Mockito.when(
                pieceRepository.save(Mockito.any(Piece.class))
        ).thenReturn(piece);
        assertEquals(pieceServiceImp.updatePiece(piece), piece);
    }

    @Test
    void updatePieceAndReturnNull(){
        Mockito.when(
            pieceRepository.save(Mockito.any(Piece.class))
        ).thenThrow(ConstraintViolationException.class);
        assertNull(pieceServiceImp.updatePiece(piece));
    }

    @Test
    void getPieceById() {
        Optional<Piece> opt = Optional.of(piece);
        Mockito.when(
            pieceRepository.findById(Mockito.anyInt())
        ).thenReturn(opt);
        assertEquals(pieceServiceImp.getPieceById(1),opt.get());
    }

    @Test
    void getPieceByIdAndReturnNull(){
        Optional<Piece> opt = Optional.empty();
        Mockito.when(
            pieceRepository.findById(Mockito.anyInt())
        ).thenReturn(opt);
        assertNull(pieceServiceImp.getPieceById(1));
    }

    @Test
    void addInStock() {
        piece.setStock(1);
        piece.setCost(1);
        piece.setCategory(new Category());
        list = new ArrayList<>();
        Mockito.when(
          stockPieceRepository.saveAll(Mockito.any(List.class))
        ).thenReturn(list);
        assertTrue(pieceServiceImp.addInStock(piece,1,1));
    }

    @Test
    void addStockReturnTrue(){
        Optional<Piece> opt = Optional.of(piece);
        piece.setStock(0);
        Mockito.when(
          pieceRepository.findById(Mockito.anyInt())
        ).thenReturn(opt);
        assertTrue(pieceServiceImp.addInStock(1,1,1));
    }

    @Test
    void addStockReturnFalse(){
        Optional<Piece> opt = Optional.empty();
        Mockito.when(
                pieceRepository.findById(Mockito.anyInt())
        ).thenReturn(opt);
        assertFalse(pieceServiceImp.addInStock(1,1,1));
    }

    @Test
    void addInStockAndReturnFalse(){
        list = new ArrayList<>();
        Mockito.when(
                stockPieceRepository.saveAll(Mockito.any(List.class))
        ).thenReturn(list);
        assertFalse(pieceServiceImp.addInStock(piece,-1,0));
    }

    @Test
    void removeInStock() {
        piece.setStock(1);
        piece.setId(1);
        Optional<Piece> opt = Optional.of(piece);
        List<StockPiece> pieces = new ArrayList<>();
        pieces.add(new StockPiece());
        pieces.add(new StockPiece());
        Mockito.when(
                pieceRepository.findById(Mockito.anyInt())
        ).thenReturn(opt);
        Mockito.when(
                stockPieceRepository.findAllByPiece_IdAndStatus(
                        Mockito.anyInt(),Mockito.anyInt()
                )
        ).thenReturn(pieces);
        Mockito.when(
                pieceRepository.save(Mockito.any(Piece.class))
        ).thenReturn(piece);
        Mockito.doNothing().when(
                stockPieceRepository
        ).deleteAll(Mockito.anyList());
        assertTrue(pieceServiceImp.removeInStock(1,1));
    }

    @Test
    void removeInStockAndReturnFalse(){
        Optional<Piece> opt = Optional.empty();
        List<StockPiece> pieces = new ArrayList<>();
        pieces.add(new StockPiece());
        pieces.add(new StockPiece());
        Mockito.when(
                pieceRepository.findById(Mockito.anyInt())
        ).thenReturn(opt);
        Mockito.when(
                stockPieceRepository.findAllByPiece_IdAndStatus(
                        Mockito.anyInt(),Mockito.anyInt()
                )
        ).thenReturn(pieces);
        Mockito.when(
                pieceRepository.save(Mockito.any(Piece.class))
        ).thenReturn(piece);
        Mockito.doNothing().when(
                stockPieceRepository
        ).deleteAll(Mockito.anyList());
        assertFalse(pieceServiceImp.removeInStock(1,1));
    }

    @Test
    void removeInStockAndReturnFalseWithAmountGratherThanAmount(){
        piece.setStock(0);
        piece.setId(1);
        Optional<Piece> opt = Optional.of(piece);
        List<StockPiece> pieces = new ArrayList<>();
        pieces.add(new StockPiece());
        pieces.add(new StockPiece());
        Mockito.when(
                pieceRepository.findById(Mockito.anyInt())
        ).thenReturn(opt);
        Mockito.when(
                stockPieceRepository.findAllByPiece_IdAndStatus(
                        Mockito.anyInt(),Mockito.anyInt()
                )
        ).thenReturn(pieces);
        Mockito.when(
                pieceRepository.save(Mockito.any(Piece.class))
        ).thenReturn(piece);
        Mockito.doNothing().when(
                stockPieceRepository
        ).deleteAll(Mockito.anyList());
        assertFalse(pieceServiceImp.removeInStock(1,1));
    }
}