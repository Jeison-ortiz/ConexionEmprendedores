package com.conexemi.emi.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idUser;
    private String firstName;
    private String lastName;
    private String email;
    private String mobile;
    private String userPassword;
    @ManyToOne(targetEntity = City.class)
    @JoinColumn(name = "city")
    private City city;



}
