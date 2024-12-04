package com.conexemi.emi.DTO;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ReactionDTO {

    private Integer idReaction;

    @NotNull(message = "The reaction cannot be empty")
    private boolean hasReacted;

    @NotNull(message = "Entrepreneurship ID cannot be null")
    private Integer idEntrepreneurship;

    private String entrepreneurshipName;

    @NotNull(message = "User ID cannot be null")
    private Integer idUser;

    private String user;

    private Integer totalReactions;
}
