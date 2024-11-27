package com.conexemi.emi.Mapper;

import com.conexemi.emi.DTO.CommentsDTO;
import com.conexemi.emi.model.Comments;
import com.conexemi.emi.model.Entrepreneurship;
import com.conexemi.emi.model.User;
import com.conexemi.emi.repositories.EntrepreneurshipRepository;
import com.conexemi.emi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class CommentsMapper {

    @Autowired
    private EntrepreneurshipRepository entrepreneurshipRepository;

    @Autowired
    private UserRepository userRepository;


    // Convert from Entity to DTO
    public static CommentsDTO toDTO(Comments comment) {
        if (comment == null) {
            return null;
        }
        return new CommentsDTO(
                comment.getIdComment(),
                comment.getCommentDescription(),
                comment.getCommentDate(),
                comment.getIdEntrepreneurship() != null ? comment.getIdEntrepreneurship().getIdEntrepreneurship() : null,
                comment.getIdUser() != null ? comment.getIdUser().getIdUser() : null
        );
    }

    // Convert from DTO to Entity
    public Comments toEntity(CommentsDTO commentDTO) {
        if (commentDTO == null) {
            return null;
        }
        Comments comment = new Comments();
        comment.setIdComment(commentDTO.getIdComment());
        comment.setCommentDescription(commentDTO.getCommentDescription());
        comment.setCommentDate(commentDTO.getCommentDate());
        // Find the Entrepreneurship and User entities from the IDs
        Entrepreneurship entrepreneurship = entrepreneurshipRepository.findById(commentDTO.getIdEntrepreneurship()).orElse(null);
        User user = userRepository.findById(commentDTO.getIdUser()).orElse(null);
        // Assign the complete objects for entrepreneurship and user
        comment.setIdEntrepreneurship(entrepreneurship);
        comment.setIdUser(user);

        return comment;
    }
}
