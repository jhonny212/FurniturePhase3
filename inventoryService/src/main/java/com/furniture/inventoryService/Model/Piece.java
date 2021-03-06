package com.furniture.inventoryService.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.io.Serializable;

@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Entity
@Table(name = "piece")
public class Piece implements Serializable {

    @Id
    @Column(name = "id_piece")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull(message = "El nombre no debe estar nulo")
    @NotBlank(message = "El nombre no debe estar vacio")
    @Column(nullable = false)
    private String name;
    @Column(nullable = true)
    private double price;
    @Range(min = 0,message = "Debe ingresar una cantidad mayor o igual a 0")
    @Column(nullable = false)
    private Integer stock;
    @NotNull(message = "Debe registrar una categoria a la pieza")
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