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
public class City {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int idCity;

  private String cityName;

  @OneToMany(targetEntity = User.class,fetch = FetchType.LAZY,mappedBy = "idCity")
  private List<User> users;

  @OneToMany(targetEntity = Entrepreneurship.class,fetch = FetchType.LAZY,mappedBy = "idCity")
  private List<Entrepreneurship> entrepreneurships;

}
