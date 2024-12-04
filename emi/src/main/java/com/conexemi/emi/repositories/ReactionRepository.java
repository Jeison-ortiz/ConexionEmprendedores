package com.conexemi.emi.repositories;

import com.conexemi.emi.model.Reaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReactionRepository extends JpaRepository<Reaction, Integer> {

    @Query("SELECT COUNT(c) FROM Comments c WHERE c.idEntrepreneurship.idEntrepreneurship = :idEntrepreneurship")
    Integer countReactionsByEntrepreneurship(@Param("idEntrepreneurship") Integer idEntrepreneurship);

}
