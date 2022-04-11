package com.furniture.inventoryService.Model;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Entity
@Table(name = "category")
public class Category implements Serializable{
    @Id
    @Column(name = "id_category")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = true)
    private boolean status = true;
    @NotNull(message = "Debe registrar un nombre a la categoria")
    @NotBlank(message = "El nombre no puede estar vacio")
    @Column(nullable = false,unique=true)
    private String name;
    @Transient
    public String msj="";
    public Category(){
        this.msj = "";
    }
    public Category(String name){
        this.msj = "";
        this.name= name;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
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
    public boolean isStatus() {
        return status;
    }
    public void setStatus(boolean status) {
        this.status = status;
    }
    public boolean getStatus(){
        return  this.status;
    }

    
}