package com.conexemi.emi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Category {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  private String nameCategory;

  public Category(int id, String nameCategory) {
    this.id = id;
    this.nameCategory = nameCategory;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getNameCategory() {
    return nameCategory;
  }

  public void setNameCategory(String nameCategory) {
    this.nameCategory = nameCategory;
  }
}
