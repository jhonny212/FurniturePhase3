package com.furniture.inventoryService.Model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "assign_furniture_piece")
public class AssignFurniturePiece implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_stock_piece",nullable = false)
    private StockPiece stockPiece;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "code_furniture",nullable = false)
    private Furniture furniture;

    public AssignFurniturePiece(Integer id, StockPiece stockPiece, Furniture furniture) {
        this.id = id;
        this.stockPiece = stockPiece;
        this.furniture = furniture;
    }

    public AssignFurniturePiece(){}

    @Override
    public String toString() {
        return "AssignFurniturePiece{" +
                "id=" + id +
                ", stockPiece=" + stockPiece +
                ", furniture=" + furniture +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public StockPiece getStockPiece() {
        return stockPiece;
    }

    public void setStockPiece(StockPiece stockPiece) {
        this.stockPiece = stockPiece;
    }

    public Furniture getFurniture() {
        return furniture;
    }

    public void setFurniture(Furniture furniture) {
        this.furniture = furniture;
    }
}