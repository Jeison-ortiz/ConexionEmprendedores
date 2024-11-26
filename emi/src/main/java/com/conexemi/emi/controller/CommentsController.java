package com.conexemi.emi.controller;

import com.conexemi.emi.model.Comments;
import com.conexemi.emi.services.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/comments")
public class CommentsController {


    @Autowired
    private CommentsService commentsService;

    @PostMapping
    public ResponseEntity<Comments> createComments(@RequestBody Comments comment) {
        Comments saveComments = commentsService.createComments(comment);
        return new ResponseEntity<>(saveComments, HttpStatus.CREATED);
    }

    @GetMapping("/id/{idComment}")
    public ResponseEntity<Comments> getCommentById(@PathVariable Integer idComment) {
        Optional<Comments> comment = commentsService.getCommentById(idComment);
        return comment.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Comments>> getAllComments() {
        List<Comments> comments = commentsService.getAllComments();
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    @DeleteMapping("/id/{idComments}")
    public ResponseEntity<Void> deleteCommentById(@PathVariable Integer idComment) {
        commentsService.deleteCommentById(idComment);
        return ResponseEntity.noContent().build();
    }


}
