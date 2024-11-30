package com.conexemi.emi.Mapper;

import com.conexemi.emi.DTO.ReactionDTO;
import com.conexemi.emi.model.Entrepreneurship;
import com.conexemi.emi.model.Reaction;
import com.conexemi.emi.model.User;
import com.conexemi.emi.repositories.EntrepreneurshipRepository;
import com.conexemi.emi.repositories.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class ReactionMapper {

    // Convert from Entity to DTO
    public static ReactionDTO toDTO(Reaction reaction) {
        return new ReactionDTO(
                reaction.getIdReaction(),
                reaction.isHasReacted(),
                reaction.getIdEntrepreneurship().getIdEntrepreneurship(),
                reaction.getIdUser().getIdUser()
        );
    }

    // Convert from DTO to Entity
    public static Reaction toEntity(ReactionDTO reactionDTO,
                                    EntrepreneurshipRepository entrepreneurshipRepository,
                                    UserRepository userRepository) {
        // Get related objects
        Entrepreneurship entrepreneurship = entrepreneurshipRepository.findById(reactionDTO.getIdEntrepreneurship())
                .orElseThrow(() -> new IllegalArgumentException("Entrepreneurship not found"));
        User user = userRepository.findById(reactionDTO.getIdUser())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        // Create the Reaction entity
        Reaction reaction = new Reaction();
        reaction.setIdReaction(reactionDTO.getIdReaction());
        reaction.setHasReacted(reactionDTO.isHasReacted());
        reaction.setIdEntrepreneurship(entrepreneurship);
        reaction.setIdUser(user);
        return reaction;
    }

}
