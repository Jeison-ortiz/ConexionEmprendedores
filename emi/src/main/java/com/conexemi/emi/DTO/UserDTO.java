package com.conexemi.emi.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTO {

    private Integer idUser;
    private String firstName;
    private String lastName;
    private String email;
    private String mobile;
    private String userPassword;
    private Integer idCity;
    private List<Integer> idRoles;

}
