package com.conexemi.emi.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UpdateEntrepreneurshipDTO {

    private String entrepreneurshipName;
    private String entrepreneurshipDescription;
    private String image;
    private String address;
    private Integer idCity;
    private Integer idUser;
    private List<Integer> idCategories;

}
