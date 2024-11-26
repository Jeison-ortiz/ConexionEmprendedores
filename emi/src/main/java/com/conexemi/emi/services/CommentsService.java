package com.conexemi.emi.services;

import com.conexemi.emi.model.Comments;
import com.conexemi.emi.repositories.CommentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentsService {

    @Autowired
    private CommentsRepository commentsRepository;

    public Comments createComments(Comments comment) {
        return commentsRepository.save(comment);
    }

    public Optional<Comments> getCommentById(Integer idComment) {
        return commentsRepository.findById(idComment);
    }

    public List<Comments> getAllComments() {
        return commentsRepository.findAll();
    }

    public void deleteCommentById(Integer idComment) {
        commentsRepository.deleteById(idComment);
    }


}
