package com.furniture.inventoryService.Model.reports;

public class BestSellerReport {
    private int idUsuario;
    private int salesAmount;
    private String name, surname;

    public BestSellerReport(int idUsuario, int salesAmount, String name, String surname) {
        this.idUsuario = idUsuario;
        this.salesAmount = salesAmount;
        this.name = name;
        this.surname = surname;
    }

    public BestSellerReport() {
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getSalesAmount() {
        return salesAmount;
    }

    public void setSalesAmount(int salesAmount) {
        this.salesAmount = salesAmount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
