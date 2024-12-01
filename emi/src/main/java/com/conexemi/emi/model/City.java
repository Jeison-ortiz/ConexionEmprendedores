package com.conexemi.emi.model;

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
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCity;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 100, unique = true)
    private CityType nameCity;

    @OneToMany(targetEntity = User.class, fetch = FetchType.LAZY, mappedBy = "idCity")
    @JsonManagedReference
    private List<User> users;

    @OneToMany(targetEntity = Entrepreneurship.class, fetch = FetchType.LAZY, mappedBy = "idCity")
    @JsonManagedReference
    private List<Entrepreneurship> entrepreneurships;

}
