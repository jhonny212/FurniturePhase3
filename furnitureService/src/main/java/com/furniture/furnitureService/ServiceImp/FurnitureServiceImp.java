package com.furniture.furnitureService.ServiceImp;

import com.furniture.furnitureService.Model.*;
import com.furniture.furnitureService.Repository.*;
import com.furniture.furnitureService.Service.FurnitureService;
import com.furniture.furnitureService.config.JWTAuthorizationFilter;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class FurnitureServiceImp implements FurnitureService {

    @Autowired
    private FurnitureRepository furnitureRepository;
    @Autowired
    private AssignPlanPieceRepository assignPlanPieceRepository;
    @Autowired
    private StockPieceRepository stockPieceRepository;
    @Autowired
    private PieceRepository pieceRepository;
    @Autowired
    private AssignFurniturePieceRepository assignFurniturePieceRepository;

    public ResponseEntity<Furniture> postRegisterFurniture(Furniture furniture, MultipartFile file, String plan, String token) {

        if (this.isExisteFurniture(furniture.getCode())){
            furniture.msj = "Ya existe un Mueble con el mismo Codigo";
            return new ResponseEntity<>(furniture,HttpStatus.BAD_REQUEST);
        }

        if(!file.isEmpty()){
            //String nameFile = utilityService.saveFile(file,"src/main/resources/img/");
            //furniture.setPath(nameFile);
        }else{
            furniture.setPath(null);
        }

        List<AssignPlanPiece> assignPlanPieces = this.assignPlanPieceRepository.findAllByPlan_Id(Integer.parseInt(plan));
        List<StockPiece> stockPieceList=new ArrayList<>();
        List<AssignFurniturePiece> assignFurniturePieceList = new ArrayList<>();
        double costo_total = 0;
        List<Piece> listP = new ArrayList<>();
        for (int i = 0; i < assignPlanPieces.size(); i++) {
            AssignPlanPiece a = assignPlanPieces.get(i);
            int id = a.getPiece().getId();
            int amount = assignPlanPieces.get(i).getAmount();
            Piece pieceTmp = a.getPiece();
            pieceTmp.setStock(pieceTmp.getStock()-amount);
            List<StockPiece>  tmp = stockPieceRepository.findAllByPiece_IdAndStatus(id,0);
            if(tmp.size()<amount){
                furniture.msj = "No hay piezas suficientes para armar el mueble "+a.getPiece().getName();
                return new ResponseEntity<>(furniture,HttpStatus.BAD_REQUEST);
            }
            for (int j = 0; j < amount; j++) {
                tmp.get(j).setStatus(1);
                stockPieceList.add(tmp.get(j));
                costo_total+=tmp.get(j).getCost();
                AssignFurniturePiece assignFurniturePiece = new AssignFurniturePiece();
                assignFurniturePiece.setFurniture(furniture);
                assignFurniturePiece.setStockPiece(tmp.get(j));
                assignFurniturePieceList.add(assignFurniturePiece);
            }
        }
        furniture.setCost(costo_total);
        Furniture tmp = this.postFurniture(furniture);
        if(tmp!=null && stockPieceList.size()!=0 ){
            this.stockPieceRepository.saveAll(stockPieceList);
            this.assignFurniturePieceRepository.saveAll(assignFurniturePieceList);
            this.pieceRepository.saveAll(listP);
            return new ResponseEntity<>(tmp,HttpStatus.OK);
        }
        furniture.msj  = "Error al crear mueble, intente de nuevo";
        return new ResponseEntity<>(furniture,HttpStatus.BAD_REQUEST);
    }

    @Override
    public Furniture postFurniture(Furniture furniture){
        try{
//            if (isExisteFurniture(furniture.getCode())){
//                furniture.msj = "Ya existe un Mueble con el mismo Codigo";
//                return furniture;
//            }
            return this.furnitureRepository.save(furniture);
        }catch (DataIntegrityViolationException e){
            furniture.msj = "Ocurrio un Error al Registrar el Mueble";
            System.out.println(e);
            return null;
        }

    }

    @Override
    public boolean putOnSale(Integer id) {
        Furniture tmp = furnitureRepository.findById(id).orElse(null);
        if(tmp!=null){
            tmp.setStatus(1);
            furnitureRepository.save(tmp);
            return true;
        }
        return false;
    }

    @Override
    public Page<Furniture> getAllFurniture(Optional<String> filter, Optional<Integer> page){
        if (filter.isPresent()){
            return this.furnitureRepository.findByNameContains(
                    filter.orElse(""),
                    PageRequest.of(page.orElse(0),15, Sort.by("name").descending())
            );
        }

        return getAllFurniture(page);
    }

    @Override
    public Page<Furniture> getAllFurniture(Optional<Integer> page){
        List<Furniture> l = this.furnitureRepository.findAll();
        return this.furnitureRepository.findAll(
                PageRequest.of(
                        page.orElse(0),
                        15
                )
        );
    }

    @Override
    public Page<Furniture> getAllFurnitureFilter(Optional<String> date1, Optional<String> date2, Optional<Integer> sort, Optional<Integer> page) throws ParseException {
        SimpleDateFormat formatter1=new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat formatter2=new SimpleDateFormat("yyyy-MM-dd");
        Date dates1 = formatter1.parse("0001-01-01");
        Date dates2 = formatter2.parse("9999-01-01");
        if (!date1.isEmpty()){
            dates1 = formatter1.parse(date1.get());
        }

        if (!date2.isEmpty()){
            dates2 = formatter2.parse(date2.get());
        }

        Optional<Date> d1 = Optional.of(dates1);
        Optional<Date> d2 = Optional.of(dates2);

        Sort s = Sort.by("name").ascending();
        if (!sort.isEmpty() && sort.get()==0){
            s = Sort.by("name").descending();
        }

        return this.furnitureRepository.findByCreationDateBetween(d1.orElse(new SimpleDateFormat("yyyy-MM-dd").parse("0001-01-01")),
                d2.orElse(new SimpleDateFormat("yyyy-MM-dd").parse("9999-01-01")),
                PageRequest.of(page.orElse(0), 10, s ));
    }

    public boolean isExisteFurniture(Integer id){
        Optional<Furniture> f = this.furnitureRepository.findById(id);
        if (f.isPresent()){
            return true;
        }
        return false;
    }

    @Override
    public Furniture getFurniture(Integer id){
        Optional<Furniture> tmp = this.furnitureRepository.findById(id);
        return tmp.orElseGet(() -> new Furniture("No existe el mueble"));
    }
}
