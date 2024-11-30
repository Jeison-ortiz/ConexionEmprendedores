package com.conexemi.emi.repositories;

import com.conexemi.emi.model.Comments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentsRepository extends JpaRepository<Comments, Integer> {
}
