package com.conexemi.emi.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
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

    @Column(nullable = false, unique = true)
    private String cityName;

    @OneToMany(targetEntity = User.class, fetch = FetchType.LAZY, mappedBy = "idCity")
    @JsonBackReference
    private List<User> users;

    @OneToMany(targetEntity = Entrepreneurship.class, fetch = FetchType.LAZY, mappedBy = "idCity")
    private List<Entrepreneurship> entrepreneurships;

    // Additional constructor for deserialization
    public City(Integer idCity) {
        this.idCity = idCity;
    }

}
