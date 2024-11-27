package com.conexemi.emi.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
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
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTO {

    private Integer idUser;

    @NotEmpty(message = "The name cannot be empty")
    private String firstName;

    @NotEmpty(message = "Last name cannot be empty")
    private String lastName;

    @NotEmpty(message = "The email cannot be empty")
    private String email;

    private String mobile;

    @NotEmpty(message = "The password cannot be empty")
    private String userPassword;

    @NotNull(message = "City ID cannot be null")
    private Integer idCity;

    @NotNull(message = "Roles cannot be null")
    @NotEmpty(message = "You must provide at least one roles")
    private List<Integer> idRoles;

}
