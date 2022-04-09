package com.furniture.saleService.Model.reports;

public class EarningsReport {
    private int invoiceNumber, codeFurniture;
    private double totalPrice, cost, lost, earning;
    private String furniture;

    public EarningsReport(int invoiceNumber, int codeFurniture, double totalPrice, double cost, double lost, double earning, String furniture) {
        this.invoiceNumber = invoiceNumber;
        this.codeFurniture = codeFurniture;
        this.totalPrice = totalPrice;
        this.cost = cost;
        this.lost = lost;
        this.earning = earning;
        this.furniture = furniture;
    }

    public EarningsReport() {
    }

    public int getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(int invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public int getCodeFurniture() {
        return codeFurniture;
    }

    public void setCodeFurniture(int codeFurniture) {
        this.codeFurniture = codeFurniture;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public double getLost() {
        return lost;
    }

    public void setLost(double lost) {
        this.lost = lost;
    }

    public double getEarning() {
        return earning;
    }

    public void setEarning(double earning) {
        this.earning = earning;
    }

    public String getFurniture() {
        return furniture;
    }

    public void setFurniture(String furniture) {
        this.furniture = furniture;
    }
}
