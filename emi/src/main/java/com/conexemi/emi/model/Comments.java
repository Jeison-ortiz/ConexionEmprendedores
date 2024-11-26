package com.conexemi.emi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Comments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idComment;

    private String commentDescription;
    private LocalDate commentDate;

    @ManyToOne(targetEntity = Entrepreneurship.class)
    @JoinColumn(name = "idEntrepreneurship")
    private Entrepreneurship idEntrepreneurship;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "idUser")
    private User idUser;



}
