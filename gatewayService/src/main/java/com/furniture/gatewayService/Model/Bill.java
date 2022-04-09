package com.furniture.gatewayService.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Entity
@Table(name = "bill")
public class Bill implements Serializable {
    @Id
    @Column(name = "id_bill")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private java.util.Date dateTime;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_user",nullable = false)
    private Profile profile;
    @Column(nullable = false)
    private double total;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "nit", nullable = false)
    private Client client;
//    @OneToMany(mappedBy = "bill", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<BillDetails> details;

    public Bill(){}

    public Bill(Integer id, Date date_time, Profile profile, double total,Client client) {
        this.id = id;
        this.dateTime = date_time;
        this.profile = profile;
        this.total = total;
        this.client = client;
//        this.details = details;
    }

    @Override
    public String toString() {
        return "Bill{" +
                "id=" + id +
                ", date_time=" + dateTime+
                ", profile=" + profile +
                ", total=" + total +
                ", client=" + client +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public java.util.Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(java.util.Date dateTime) {
        this.dateTime = dateTime;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

//    public List<BillDetails> getDetails() {
//        return details;
//    }
//
//    public void setDetails(List<BillDetails> details) {
//        this.details = details;
//    }
}