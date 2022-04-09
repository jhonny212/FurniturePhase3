package com.furniture.gatewayService.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@JsonIgnoreProperties({"hibernateLazyInitializer"})
//@JsonIgnoreProperties({ "", "lastModifiedBy" })
@Entity
@Table(name = "bill_details")
public class BillDetails implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_bill")
    private Bill bill;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_furniture",nullable = false)
    private Furniture furniture;
    @Temporal(TemporalType.DATE)
    @Column(name="date_return")
    private java.util.Date dateReturn;
    @Column(nullable = false,name="price_sale")
    private double priceSale;
    @Column(name = "cost_lost",columnDefinition = "double precision default 0")
    private double costLost=0;

    public BillDetails(Integer id, Bill bill, Furniture furniture, Date dateReturn, double priceSale) {
        this.id = id;
        this.bill = bill;
        this.furniture = furniture;
        this.dateReturn = dateReturn;
        this.priceSale = priceSale;
        costLost = 0;
    }

    public BillDetails(Integer id, Bill bill, Furniture furniture, double priceSale) {
        this.id = id;
        this.bill = bill;
        this.furniture = furniture;
        this.priceSale = priceSale;
        costLost = 0;
    }

    public BillDetails(){
        costLost = 0;
    }

    @Override
    public String toString() {
        return "BillDetails{" +
                "id=" + id +
                ", bill=" + bill +
                ", furniture=" + furniture +
                ", dateReturn=" + dateReturn +
                ", priceSale=" + priceSale +
                '}';
    }

    public double getCostLost() {
        return costLost;
    }

    public void setCostLost(double costLost) {
        this.costLost = costLost;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Bill getBill() {
        return bill;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
    }

    public Furniture getFurniture() {
        return furniture;
    }

    public void setFurniture(Furniture furniture) {
        this.furniture = furniture;
    }

    public Date getDateReturn() {
        return dateReturn;
    }

    public void setDateReturn(Date dateReturn) {
        this.dateReturn = dateReturn;
    }

    public double getPriceSale() {
        return priceSale;
    }

    public void setPriceSale(double priceSale) {
        this.priceSale = priceSale;
    }
}