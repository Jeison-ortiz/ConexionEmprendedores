package com.conexemi.emi.repositories;

import com.conexemi.emi.model.Comments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentsRepository extends JpaRepository<Comments, Integer> {

    @Query("SELECT c FROM Comments c " +
            "JOIN c.idEntrepreneurship e " +
            "WHERE e.idEntrepreneurship = :idEntrepreneurship")
    List<Comments> getCommentsByEntrepreneurship(@Param("idEntrepreneurship") Integer idEntrepreneurship);

}
