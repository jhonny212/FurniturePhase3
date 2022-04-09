package com.furniture.salesReportService.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Entity
@Table(name = "furniture")
public class Furniture implements Serializable {
    @Id
    private Integer code;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private double price;
    @Column(nullable = false)
    private double cost;
    @Temporal(TemporalType.DATE)
    @Column(nullable = false,name="creation_date")
    private java.util.Date creationDate;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String path;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_user",nullable = false)
    private Profile profile;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_plan",nullable = false)
    private Plan plan;
    @Column(nullable = false)
    private Integer status;
    @Transient
    public String msj="";
    @Transient
    public double amount;

    public Furniture(Integer code, String name, double price, double cost, Date creationDate, String description, String path, Profile profile, Plan plan, Integer status) {
        this.code = code;
        this.name = name;
        this.price = price;
        this.cost = cost;
        this.creationDate = creationDate;
        this.description = description;
        this.path = path;
        this.profile = profile;
        this.plan = plan;
        this.status = status;
        this.msj = "";
    }

    public Furniture(Integer code, String name, double price, double cost, Date creationDate, String description, String path, Profile profile, Plan plan, Integer status, double amount) {
        this.code = code;
        this.name = name;
        this.price = price;
        this.cost = cost;
        this.creationDate = creationDate;
        this.description = description;
        this.path = path;
        this.profile = profile;
        this.plan = plan;
        this.status = status;
        this.msj = "";
        this.amount = amount;
    }

    public Furniture(){
        this.msj = "";
    }
    public Furniture(String msj){
        this.msj = msj;
    }
    @Override
    public String toString() {
        return "Furniture{" +
                "code=" + code +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", cost=" + cost +
                ", creationDate=" + creationDate +
                ", description='" + description + '\'' +
                ", path='" + path + '\'' +
                ", profile=" + profile +
                ", plan=" + plan +
                ", status=" + status +
                '}';
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
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

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public Plan getPlan() {
        return plan;
    }

    public void setPlan(Plan plan) {
        this.plan = plan;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}