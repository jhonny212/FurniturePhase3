package com.furniture.furnitureService.ServiceImp;

import com.furniture.furnitureService.Model.*;
import com.furniture.furnitureService.Repository.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.web.multipart.MultipartFile;

import static org.junit.jupiter.api.Assertions.*;

class FurnitureServiceImpTest {
    @Mock
    private FurnitureRepository furnitureRepository;
    @Mock
    private AssignPlanPieceRepository assignPlanPieceRepository;
    @Mock
    private StockPieceRepository stockPieceRepository;
    @Mock
    private PieceRepository pieceRepository;
    @Mock
    private AssignFurniturePieceRepository assignFurniturePieceRepository;
    @InjectMocks
    private FurnitureServiceImp furnitureServiceImp;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void putOnSale() {
        Furniture tmp = new Furniture();
        Optional<Furniture> opt = Optional.of(tmp);
        Mockito.when(
                furnitureRepository.findById(Mockito.anyInt())
        ).thenReturn(opt);
        Mockito.when(
                furnitureRepository.save(tmp)
        ).thenReturn(tmp);
        assertTrue(furnitureServiceImp.putOnSale(1));
    }

    @Test
    void putOnSaleAndReturnFalse(){
        Optional<Furniture> tmp = Optional.empty();
        Mockito.when(
                furnitureRepository.findById(Mockito.anyInt())
        ).thenReturn(tmp);
        assertFalse(furnitureServiceImp.putOnSale(1));
    }


    @Test
    void postRegisterFurniture() {
        Furniture furniture = new Furniture();
        List<AssignPlanPiece> assignPlanPieces = new ArrayList<>();
        List<StockPiece> stockPieceList = new ArrayList<>();
        List<AssignFurniturePiece> assignFurniturePieceList = new ArrayList<>();
        List<Piece> listPieces = new ArrayList<>();
        MockMultipartFile file
                = new MockMultipartFile(
                "file",
                "hello.jpg",
                MediaType.TEXT_PLAIN_VALUE,
                "Hello, World!".getBytes()
        );

        Mockito.when(
                assignPlanPieceRepository.findAllByPlan_Id(Mockito.anyInt())
        ).thenReturn(assignPlanPieces);

        Mockito.when(
                stockPieceRepository.findAllByPiece_IdAndStatus(Mockito.anyInt(), Mockito.anyInt())
        ).thenReturn(stockPieceList);

        Mockito.when(
                stockPieceRepository.saveAll(Mockito.any())
        ).thenReturn(stockPieceList);

        Mockito.when(
                assignFurniturePieceRepository.saveAll(Mockito.any())
        ).thenReturn(assignFurniturePieceList);

        Mockito.when(
                pieceRepository.saveAll(Mockito.any())
        ).thenReturn(listPieces);

        assertNotEquals(furnitureServiceImp.postRegisterFurniture(new Furniture(), file, Optional.of("1").get(), Optional.of("1").get()), furniture);

    }

    @Test
    void getAllFurniture() {
        Page<Furniture> list = Page.empty();
        Mockito.when(
                furnitureRepository.findByNameContains(Mockito.anyString(), Mockito.any(Pageable.class))
        ).thenReturn(list);
        assertEquals(furnitureServiceImp.getAllFurniture(Optional.of(""), Optional.of(0)),list);
    }

    @Test
    void testGetAllFurniture() {
        Page<Furniture> list = Page.empty();
        Mockito.when(
                furnitureRepository.findAll(Mockito.any(Pageable.class))
        ).thenReturn(list);
        assertEquals(furnitureServiceImp.getAllFurniture(Optional.of(0)),list);
    }

    @Test
    void getAllFurnitureFilter() throws ParseException {
        Page<Furniture> list = Page.empty();
        Mockito.when(
                furnitureRepository.findByCreationDateBetween(Mockito.any(), Mockito.any(), Mockito.any(Pageable.class))
        ).thenReturn(list);
        assertEquals(furnitureServiceImp.getAllFurnitureFilter(Optional.of("2022-01-01"), Optional.of("2022-04-01"), Optional.of(1), Optional.of(0)),list);
    }

    @Test
    void isExisteFurniture() {
        Optional<Furniture> f = Optional.empty();
        Mockito.when(
                furnitureRepository.findById(Mockito.anyInt())
        ).thenReturn(f);
        assertFalse(furnitureServiceImp.isExisteFurniture(Optional.of(0).get()));
    }

    @Test
    void getFurniture() {
        Optional<Furniture> f = Optional.empty();
        Furniture furniture = new Furniture();
        Mockito.when(
                furnitureRepository.findById(Mockito.anyInt())
        ).thenReturn(f);
        assertNotEquals(furnitureServiceImp.getFurniture(Optional.of(0).get()), f);
    }
}