package com.conexemi.emi.DTO;

import com.conexemi.emi.model.CityType;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class CityDTO {

    private Integer idCity;

    @NotEmpty(message = "City name cannot be empty")
    private CityType nameCity;

}
