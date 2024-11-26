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
    private Integer idUser;

    @Column(nullable = false, length = 150)
    private String firstName;

    @Column(nullable = false, length = 150)
    private String lastName;

    @Column(nullable = false, length = 150)
    private String email;

    @Column(nullable = false, length = 150)
    private String mobile;

    @Column(nullable = false, length = 150)
    private String userPassword;

    @ManyToOne(targetEntity = City.class)
    @JoinColumn(name = "idCity")
    private City idCity;

    @ManyToMany(targetEntity = Role.class, fetch = FetchType.LAZY)
    @JoinTable(name = "userRole",
            joinColumns = @JoinColumn(name = "idUser"),
            inverseJoinColumns = @JoinColumn(name = "idRole"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"idUser", "idRole"})
    )
    private List<Role> roles;

    @OneToMany(targetEntity = Entrepreneurship.class, fetch = FetchType.LAZY, mappedBy = "idUser")
    private List<Entrepreneurship> entrepreneurships;

    @OneToMany(targetEntity = Comments.class, fetch = FetchType.LAZY, mappedBy = "idUser")
    private List<Comments> comments;

    @OneToMany(targetEntity = Reaction.class, fetch = FetchType.LAZY, mappedBy = "idUser")
    private List<Reaction> reactions;


    // Constructor adicional para deserialización
    public User(Integer idUser) {
        this.idUser = idUser;
    }
}
