package com.furniture.gatewayService.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;

@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Entity
@Table(name = "stock_piece")
public class StockPiece implements Serializable {

    @Id
    @Column(name = "id_sotck_piece")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private double cost;
    @Column(nullable = false)
    private int status;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_piece",nullable = false)
    private Piece piece;

    @Override
    public String toString() {
        return "StockPiece{" +
                "id=" + id +
                ", cost=" + cost +
                ", status=" + status +
                ", piece=" + piece +
                '}';
    }

    public StockPiece(){}

    public StockPiece(Integer id, double cost, int status, Piece piece) {
        this.id = id;
        this.cost = cost;
        this.status = status;
        this.piece = piece;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }
}