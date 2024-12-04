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
public class UpdateUserDTO {

    private String firstName;
    private String lastName;
    private String email;
    private String mobile;
    private String userPassword;
    private Integer idCity;
    private List<Integer> idRoles;

}
