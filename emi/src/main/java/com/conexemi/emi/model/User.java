package com.conexemi.emi.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    private Integer idUser;

    @Column(nullable = false, length = 150)
    private String firstName;

    @Column(nullable = false, length = 150)
    private String lastName;

    @Column(nullable = false, length = 150, unique = true)
    private String email;

    @Column(nullable = false, length = 150)
    private String mobile;

    @Column(nullable = false, length = 150)
    private String userPassword;

    @ManyToOne(targetEntity = City.class)
    @JoinColumn(name = "idCity")
    @JsonBackReference
    private City idCity;

    @ManyToMany(targetEntity = Role.class, fetch = FetchType.LAZY)
    @JoinTable(name = "userRole",
            joinColumns = @JoinColumn(name = "idUser"),
            inverseJoinColumns = @JoinColumn(name = "idRole"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"idUser", "idRole"})
    )
    private List<Role> roles;

    @OneToMany(targetEntity = Entrepreneurship.class, fetch = FetchType.LAZY, mappedBy = "idUser")
    @JsonManagedReference
    private List<Entrepreneurship> entrepreneurships;

    @OneToMany(targetEntity = Comments.class, fetch = FetchType.LAZY, mappedBy = "idUser")
    @JsonManagedReference
    private List<Comments> comments;

    @OneToMany(targetEntity = Reaction.class, fetch = FetchType.LAZY, mappedBy = "idUser")
    @JsonManagedReference
    private List<Reaction> reactions;

}
