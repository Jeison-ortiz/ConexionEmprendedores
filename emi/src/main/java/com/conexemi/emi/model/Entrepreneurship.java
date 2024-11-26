package com.conexemi.emi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Entrepreneurship {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEntrepreneurship;

    @Column(nullable = false, length = 150)
    private String entrepreneurshipName;

    @Column(nullable = false, length = 500)
    private String entrepreneurshipDescription;

    @Column(nullable = false, length = 150)
    private String image;

    @Column(columnDefinition = "DATETIME", nullable = false)
    private LocalDateTime publicationDate;

    @PrePersist
    protected void onCreate() {
        publicationDate = LocalDateTime.now();
    }

    @Column(nullable = false, length = 150)
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
            uniqueConstraints = @UniqueConstraint(columnNames = {"idEntrepreneurship", "idCategory"})
    )
    private List<Category> categories;

    @OneToMany(targetEntity = Comments.class, fetch = FetchType.LAZY, mappedBy = "idEntrepreneurship")
    private List<Comments> comments;

    @OneToMany(targetEntity = Reaction.class, fetch = FetchType.LAZY, mappedBy = "idEntrepreneurship")
    private List<Reaction> reactions;

    // Constructor adicional para deserialización
    public Entrepreneurship(Integer idEntrepreneurship) {
        this.idEntrepreneurship = idEntrepreneurship;
    }

}
