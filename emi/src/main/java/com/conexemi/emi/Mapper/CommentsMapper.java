package com.conexemi.emi.Mapper;

import com.conexemi.emi.DTO.CommentsDTO;
import com.conexemi.emi.model.Comments;
import com.conexemi.emi.model.Entrepreneurship;
import com.conexemi.emi.model.User;
import com.conexemi.emi.repositories.CommentsRepository;
import com.conexemi.emi.repositories.EntrepreneurshipRepository;
import com.conexemi.emi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Component
public class CommentsMapper {

    @Autowired
    private EntrepreneurshipRepository entrepreneurshipRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CommentsRepository commentsRepository;


    // Convert from Entity to DTO
    public static CommentsDTO toDTO(Comments comment, CommentsRepository commentsRepository) {
        if (comment == null) {
            return null;
        }

        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        String formattedDate = comment.getCommentDate() != null ? comment.getCommentDate().format(formatter) : null;

        Integer idEntrepreneurship = comment.getIdEntrepreneurship() != null ? comment.getIdEntrepreneurship().getIdEntrepreneurship() : null;
        Integer totalComments = idEntrepreneurship != null ? commentsRepository.countCommentsByEntrepreneurship(idEntrepreneurship): 0;

        return new CommentsDTO(
                comment.getIdComment(),
                comment.getCommentDescription(),
                formattedDate,
                idEntrepreneurship,
                comment.getIdEntrepreneurship().getEntrepreneurshipName(),
                comment.getIdUser() != null ? comment.getIdUser().getIdUser() : null,
                comment.getIdUser().getFirstName() + " " + comment.getIdUser().getLastName(),
                totalComments
        );
    }


    // Convert from DTO to Entity
    public static Comments toEntity(CommentsDTO commentDTO, EntrepreneurshipRepository entrepreneurshipRepository, UserRepository userRepository) {
        if (commentDTO == null) {
            return null;
        }

        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        LocalDateTime dateTime = commentDTO.getCommentDate() != null ? LocalDateTime.parse(commentDTO.getCommentDate(), formatter) : null;

        Comments comment = new Comments();
        comment.setIdComment(commentDTO.getIdComment());
        comment.setCommentDescription(commentDTO.getCommentDescription());
        comment.setCommentDate(dateTime);
        // Find the Entrepreneurship and User entities from the IDs
        Entrepreneurship entrepreneurship = entrepreneurshipRepository.findById(commentDTO.getIdEntrepreneurship()).orElse(null);
        User user = userRepository.findById(commentDTO.getIdUser()).orElse(null);
        // Assign the complete objects for entrepreneurship and user
        comment.setIdEntrepreneurship(entrepreneurship);
        comment.setIdUser(user);

        return comment;
    }
}
