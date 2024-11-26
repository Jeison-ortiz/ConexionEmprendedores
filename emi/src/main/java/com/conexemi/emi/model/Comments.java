package com.conexemi.emi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Comments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idComment;

    @Column(nullable = false)
    private String commentDescription;

    @Column(columnDefinition = "DATETIME", nullable = false)
    private LocalDateTime commentDate;

    @PrePersist
    protected void onCreate() {
        commentDate = LocalDateTime.now();
    }

    @ManyToOne(targetEntity = Entrepreneurship.class)
    @JoinColumn(name = "idEntrepreneurship")
    private Entrepreneurship idEntrepreneurship;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "idUser")
    private User idUser;

}
