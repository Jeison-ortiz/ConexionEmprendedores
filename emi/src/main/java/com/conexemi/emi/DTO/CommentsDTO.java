package com.conexemi.emi.DTO;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class CommentsDTO {

    private Integer idComment;

    @NotEmpty(message = "Comment description cannot be empty")
    private String commentDescription;

    private String commentDate;

    @NotNull(message = "Entrepreneurship ID cannot be null")
    private Integer idEntrepreneurship;

    private String entrepreneurshipName;

    @NotNull(message = "User ID cannot be null")
    private Integer idUser;

    private String user;


    public void setCommentDate(LocalDateTime commentDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        this.commentDate = commentDate.format(formatter);
    }

}
