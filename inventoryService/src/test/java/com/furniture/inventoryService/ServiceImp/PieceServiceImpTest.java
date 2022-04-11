package com.furniture.inventoryService.ServiceImp;

import org.hibernate.exception.ConstraintViolationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;

import com.furniture.inventoryService.Model.Piece;
import com.furniture.inventoryService.Repository.PieceRepository;

class PieceServiceImpTest {
    @Mock
    private PieceRepository pieceRepository;
    @InjectMocks
    private PieceServiceImp pieceServiceImp;
    Piece piece;
    List<Piece> list;
    
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        piece = new Piece();
    }

    @Test
    void createPiece() {
        Mockito.when(
            pieceRepository.save(Mockito.any(Piece.class))
        ).thenReturn(piece);
        assertEquals(pieceServiceImp.createPiece(piece),piece);
    }

    @Test
    void createPieceAndThrowException(){
        Mockito.when(
            pieceRepository.save(Mockito.any(Piece.class))
        ).thenThrow(ConstraintViolationException.class);
        assertNull(pieceServiceImp.createPiece(piece));
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
}