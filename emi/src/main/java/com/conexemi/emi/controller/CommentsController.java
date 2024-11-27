package com.conexemi.emi.controller;

import com.conexemi.emi.DTO.CommentsDTO;
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
    public ResponseEntity<CommentsDTO> createComments(@RequestBody CommentsDTO commentDTO) {
        CommentsDTO savedCommentDTO = commentsService.createComment(commentDTO);
        return new ResponseEntity<>(savedCommentDTO, HttpStatus.CREATED);
    }

    @GetMapping("/id/{idComment}")
    public ResponseEntity<CommentsDTO> getCommentById(@PathVariable Integer idComment) {
        Optional<CommentsDTO> commentDTO = commentsService.getCommentById(idComment);
        return commentDTO.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<CommentsDTO>> getAllComments() {
        List<CommentsDTO> commentsDTO = commentsService.getAllComments();
        return new ResponseEntity<>(commentsDTO, HttpStatus.OK);
    }

    @DeleteMapping("/id/{idComment}")
    public ResponseEntity<Void> deleteCommentById(@PathVariable Integer idComment) {
        commentsService.deleteCommentById(idComment);
        return ResponseEntity.noContent().build();
    }


}
