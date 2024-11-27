package com.conexemi.emi.services;

import com.conexemi.emi.DTO.CommentsDTO;
import com.conexemi.emi.Exceptions.ResourceNotFoundException;
import com.conexemi.emi.Mapper.CommentsMapper;
import com.conexemi.emi.model.Comments;
import com.conexemi.emi.repositories.CommentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommentsService {

    @Autowired
    private CommentsRepository commentsRepository;

    @Autowired
    private CommentsMapper commentsMapper;


    public CommentsDTO createComment(CommentsDTO commentDTO) {
        Comments comment = commentsMapper.toEntity(commentDTO);
        Comments savedComment = commentsRepository.save(comment);
        return commentsMapper.toDTO(savedComment);
    }

    public Optional<CommentsDTO> getCommentById(Integer idComment) {
        Optional<Comments> comment = Optional.ofNullable(commentsRepository.findById(idComment)
                .orElseThrow(() -> new ResourceNotFoundException("Comments with ID " + idComment + " not found")));
        return comment.map(CommentsMapper::toDTO);
    }

    public List<CommentsDTO> getAllComments() {
        List<Comments> comments = commentsRepository.findAll();
        return comments.stream().map(CommentsMapper::toDTO).collect(Collectors.toList());
    }

    public void deleteCommentById(Integer idComment) {
        commentsRepository.deleteById(idComment);
    }


}
