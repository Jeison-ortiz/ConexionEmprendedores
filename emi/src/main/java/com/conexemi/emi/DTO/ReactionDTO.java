package com.conexemi.emi.DTO;

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
    private boolean hasReacted;
    private Integer idEntrepreneurship;
    private Integer idUser;

}
