package com.conexemi.emi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

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
    @JoinColumn(name = "idCity")
    private City idCity;

    @ManyToMany(targetEntity = Role.class, fetch = FetchType.LAZY)
    @JoinTable(name = "userRole",
            joinColumns = @JoinColumn(name = "idUser"),
            inverseJoinColumns = @JoinColumn(name = "idRole"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"idUser","idRole"})
    )
    private List<Role> role;

    @OneToMany(targetEntity = Entrepreneurship.class,fetch = FetchType.LAZY,mappedBy = "idUser")
    private List<Entrepreneurship> entrepreneurships;

    @OneToMany(targetEntity = Comments.class,fetch = FetchType.LAZY,mappedBy = "idUser")
    private List<Comments> comments;

    @OneToMany(targetEntity = Reaction.class,fetch = FetchType.LAZY,mappedBy = "idUser")
    private List<Reaction> reactions;

}
