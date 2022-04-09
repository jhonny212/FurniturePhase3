package com.furniture.furnitureService.Model.reports;

import java.util.Date;

public class ReturnReport {
    private String furniture;
    private double salePrice, cost;
    private Date date;
    private int nitCliente;

    public ReturnReport(String furniture, double salePrice, double cost, Date date, int nitCliente) {
        this.furniture = furniture;
        this.salePrice = salePrice;
        this.cost = cost;
        this.date = date;
        this.nitCliente = nitCliente;
    }

    public ReturnReport() {
    }

    public String getFurniture() {
        return furniture;
    }

    public void setFurniture(String furniture) {
        this.furniture = furniture;
    }

    public double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(double salePrice) {
        this.salePrice = salePrice;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getNitCliente() {
        return nitCliente;
    }

    public void setNitCliente(int nitCliente) {
        this.nitCliente = nitCliente;
    }
}
