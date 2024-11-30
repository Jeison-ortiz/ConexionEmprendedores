package com.conexemi.emi.DTO;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class CityDTO {

    private Integer idCity;

    @NotEmpty(message = "City name cannot be empty")
    private String cityName;

}
