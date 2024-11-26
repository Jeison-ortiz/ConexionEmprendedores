package com.conexemi.emi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Entrepreneurship {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idEntrepreneurship;

    private String entrepreneurshipName;
    private String entrepreneurshipDescription;
    private String image;
    private LocalDate publicationDate;
    private String address;

    @ManyToOne(targetEntity = City.class)
    @JoinColumn(name = "idCity")
    private City idCity;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "idUser")
    private User idUser;

    @ManyToMany(targetEntity = Category.class, fetch = FetchType.LAZY)
    @JoinTable(name = "entrepreneurshipCategory",
            joinColumns = @JoinColumn(name = "idEntrepreneurship"),
            inverseJoinColumns = @JoinColumn(name = "idCategory"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"idEntrepreneurship","idCategory"})
    )
    private List<Category> categories;

    @OneToMany(targetEntity = Comments.class,fetch = FetchType.LAZY,mappedBy = "idEntrepreneurship")
    private List<Comments> comments;

    @OneToMany(targetEntity = Reaction.class,fetch = FetchType.LAZY,mappedBy = "idEntrepreneurship")
    private List<Reaction> reactions;

}
