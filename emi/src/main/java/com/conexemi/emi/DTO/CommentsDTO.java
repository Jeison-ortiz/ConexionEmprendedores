package com.conexemi.emi.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class CommentsDTO {

    private Integer idComment;
    private String commentDescription;
    private LocalDateTime commentDate;
    private Integer idEntrepreneurship;
    private Integer idUser;

}
