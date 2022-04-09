package com.furniture.adminService.Model.reports;

public class BestEarnerReport {
    private int idUsuario;
    private double earnings;
    private String name, surname;

    public BestEarnerReport(int idUsuario, double earnings, String name, String surname) {
        this.idUsuario = idUsuario;
        this.earnings = earnings;
        this.name = name;
        this.surname = surname;
    }

    public BestEarnerReport() {
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public double getEarnings() {
        return earnings;
    }

    public void setEarnings(double earnings) {
        this.earnings = earnings;
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
