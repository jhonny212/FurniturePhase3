package com.furniture.adminService.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Entity
@Table(name = "profile")
public class Profile implements Serializable{
    @Id
    @Column(name = "id_user")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull(message = "Debe registrar un nombre de usuario")
    @NotBlank(message = "El nombre de usuario no puede estar vacio")
    @Column(unique = true,nullable = false)
    private String username;
    @NotNull(message = "Debe registrar un nombre")
    @Column(nullable = false,name="first_name")
    private String firstName;
    @NotNull(message = "Debe registrar el apellido")
    @Column(nullable = false,name="last_name")
    private String lastName;
    @NotNull(message = "Debe registrar una contraseña")
    @Length(min = 8,message = "Debe ingresar una contraseña mayor o igual a 8 caracteres")
    @Column(nullable = false, columnDefinition = "TEXT")
    private String password;
    @NotNull(message = "Debe seleccionar un tipo de usuario")
    @Size(min = 0, max = 3, message = "Debe selecconar un tipo de usuario valido")
    @Column(nullable = false,name="user_type")
    private Integer userType;

    @Column(nullable = true)
    public boolean status=true;

    @Transient
    private String token;


    public Profile(Integer id, String username, String firstName, String lastName, String password, Integer userType) {
        this.id = id;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.userType = userType;
        this.status=true;

    }
    public Profile(){}
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getToken() { return token; }

    public void setToken(String token) { this.token = token; }

    public boolean getStatus(){return this.status;}

    @Override
    public String toString() {
        return "Profile{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", password='" + password + '\'' +
                ", userType=" + userType +
                ", status=" + status +
                ", token='" + token + '\'' +
                '}';
    }
}