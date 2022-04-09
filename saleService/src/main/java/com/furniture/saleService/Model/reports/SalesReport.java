package com.furniture.saleService.Model.reports;

import java.util.Date;

public class SalesReport {
    private String client, furniture;
    private int furnitureCode;
    private double price;
    private Date date;

    public SalesReport(String client, String furniture, int furnitureCode, double price, Date date) {
        this.client = client;
        this.furniture = furniture;
        this.furnitureCode = furnitureCode;
        this.price = price;
        this.date = date;
    }

    public SalesReport() {
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getFurniture() {
        return furniture;
    }

    public void setFurniture(String furniture) {
        this.furniture = furniture;
    }

    public int getFurnitureCode() {
        return furnitureCode;
    }

    public void setFurnitureCode(int furnitureCode) {
        this.furnitureCode = furnitureCode;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
