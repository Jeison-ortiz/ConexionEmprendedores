package com.conexemi.emi.services;

import com.conexemi.emi.DTO.CommentsDTO;
import com.conexemi.emi.Exceptions.ResourceNotFoundException;
import com.conexemi.emi.Mapper.CommentsMapper;
import com.conexemi.emi.model.Comments;
import com.conexemi.emi.repositories.CommentsRepository;
import com.conexemi.emi.repositories.EntrepreneurshipRepository;
import com.conexemi.emi.repositories.UserRepository;
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
    private EntrepreneurshipRepository entrepreneurshipRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    public CommentsService(CommentsRepository commentsRepository) {
        this.commentsRepository = commentsRepository;
    }


    public CommentsDTO createComment(CommentsDTO commentDTO) {
        Comments comment = CommentsMapper.toEntity(commentDTO, entrepreneurshipRepository, userRepository);
        Comments savedComment = commentsRepository.save(comment);
        return CommentsMapper.toDTO(savedComment, commentsRepository);
    }

    public Optional<CommentsDTO> getCommentById(Integer idComment) {
        Comments comment = commentsRepository.findById(idComment)
                .orElseThrow(() -> new ResourceNotFoundException("Comment with ID " + idComment + " not found"));
        return Optional.of(CommentsMapper.toDTO(comment, commentsRepository));
    }

    public List<CommentsDTO> getAllComments() {
        List<Comments> comments = commentsRepository.findAll();
        return comments.stream().map(comment -> CommentsMapper.toDTO(comment, commentsRepository)).collect(Collectors.toList());
    }

    public void deleteCommentById(Integer idComment) {
        if (!commentsRepository.existsById(idComment)) {
            throw new ResourceNotFoundException("Comment with ID " + idComment + " not found");
        }
        commentsRepository.deleteById(idComment);
    }

    public List<CommentsDTO> getCommentsByEntrepreneurship(Integer idEntrepreneurship) {
        List<Comments> comentsList = commentsRepository.getCommentsByEntrepreneurship(idEntrepreneurship);
        return comentsList.stream().map(comment -> CommentsMapper.toDTO(comment, commentsRepository)).collect(Collectors.toList());
    }


}
