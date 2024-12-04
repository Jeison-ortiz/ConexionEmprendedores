package com.conexemi.emi.DTO;

import com.conexemi.emi.model.CategoryType;
import com.conexemi.emi.model.CityType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class EntrepreneurshipDTO {

    private Integer idEntrepreneurship;

    @NotEmpty(message = "The name of the business cannot be empty.")
    private String entrepreneurshipName;

    @NotEmpty(message = "The description of the venture cannot be empty")
    private String entrepreneurshipDescription;

    private String image;

    private String address;

    @NotNull(message = "City ID cannot be null")
    private Integer idCity;

    private CityType nameCity;

    @NotNull(message = "User ID cannot be null")
    private Integer idUser;

    private String user;

    @NotNull(message = "Categories cannot be null")
    @NotEmpty(message = "You must provide at least one category")
    private List<Integer> idCategories;

    private List<CategoryType> nameCategories;

}
