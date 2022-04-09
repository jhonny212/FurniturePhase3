package com.furniture.gatewayService.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.io.Serializable;

@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Entity
@Table(name = "piece")
public class Piece implements Serializable {

    @Id
    @Column(name = "id_piece")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private double price;
    @Column(nullable = false)
    private Integer stock;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_category",nullable = false)
    @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
    private Category category;
    @Transient
    public String msj="";
    @Transient
    public double cost=0;
    @Transient
    public int cod = 0;

    public Piece(Integer id, String name, double price, Integer stock, Category piece) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.cost = price;
        this.stock = stock;
        this.msj = "";
    }

    public Piece(String msj){
        this.msj = msj;
    }

    public Piece() {
        this.msj = "";
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "Piece{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                ", category=" + category +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}