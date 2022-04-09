package com.furniture.adminService.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.*;
import java.io.Serializable;

@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Entity
@Table(name = "furniture_in_bill")
public class FurnitureInBill implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "code",nullable = false)
    private Furniture furniture;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_user",nullable = false)
    private Profile profile;

    public FurnitureInBill(Furniture furniture, Profile profile) {
        this.furniture = furniture;
        this.profile = profile;
    }

    public FurnitureInBill() {}

    public Furniture getFurniture() {
        return furniture;
    }

    public void setFurniture(Furniture furniture) {
        this.furniture = furniture;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public Integer getId() { return id; }

    public void setId(Integer id){ this.id = id; }

    @Override
    public String toString() {
        return "FurnitureInBill{" +
                "id=" + id +
                ", furniture=" + furniture +
                ", profile=" + profile +
                '}';
    }
}
