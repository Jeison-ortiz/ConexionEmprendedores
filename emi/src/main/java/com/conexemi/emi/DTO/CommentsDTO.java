package com.conexemi.emi.DTO;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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

    @NotEmpty(message = "Comment description cannot be empty")
    private String commentDescription;

    private LocalDateTime commentDate;

    @NotNull(message = "Entrepreneurship ID cannot be null")
    private Integer idEntrepreneurship;

    @NotNull(message = "User ID cannot be null")
    private Integer idUser;

}
