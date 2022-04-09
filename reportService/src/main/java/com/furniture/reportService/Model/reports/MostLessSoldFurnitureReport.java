package com.furniture.reportService.Model.reports;

import java.util.Date;

public class MostLessSoldFurnitureReport {
    private int codeFurniture, amount;
    private String nameFurniture;
    private Date date;
    private double price;

    public MostLessSoldFurnitureReport(int codeFurniture, String nameFurniture, Date date, double price, int amount) {
        this.codeFurniture = codeFurniture;
        this.nameFurniture = nameFurniture;
        this.date = date;
        this.price = price;
        this.amount = amount;
    }

    public MostLessSoldFurnitureReport() {
    }

    public int getCodeFurniture() {
        return codeFurniture;
    }

    public void setCodeFurniture(int codeFurniture) {
        this.codeFurniture = codeFurniture;
    }

    public String getNameFurniture() {
        return nameFurniture;
    }

    public void setNameFurniture(String nameFurniture) {
        this.nameFurniture = nameFurniture;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
