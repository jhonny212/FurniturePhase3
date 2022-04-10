package com.furniture.saleService.Util;

public enum CONST {
    AUTHORIZATION_HEADER("Authorization");

    private final String value;

    CONST(String value){
        this.value = value;
    }

    public String value(){ return value; }
}
