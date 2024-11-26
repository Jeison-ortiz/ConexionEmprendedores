package com.conexemi.emi.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
public class Reaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idReaction;

    @Column(nullable = false)
    private boolean hasReacted;

    @ManyToOne(targetEntity = Entrepreneurship.class)
    @JoinColumn(name = "idEntrepreneurship")
    @JsonBackReference
    private Entrepreneurship idEntrepreneurship;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "idUser")
    @JsonBackReference
    private User idUser;

}
