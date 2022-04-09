package com.furniture.adminService.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;

@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Entity
@Table(name = "client")
public class Client implements Serializable {
    @Column(name = "nit")
    @Id private Integer id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String address;

    public Client(Integer id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    public Client(){}

    @Override
    public String toString() {
        return "Client{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", address='" + address + '\'' +
                '}';
    }

    public Integer getId() { return id; }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}