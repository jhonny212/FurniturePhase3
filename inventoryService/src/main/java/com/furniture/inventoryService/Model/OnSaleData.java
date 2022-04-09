package com.furniture.inventoryService.Model;

public class OnSaleData {
    private Integer code;
    private Double price;

    public OnSaleData(Integer code, Double price) {
        this.code = code;
        this.price = price;
    }

    public OnSaleData(){}

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
