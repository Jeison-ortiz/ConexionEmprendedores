package com.conexemi.emi.Mapper;

import com.conexemi.emi.DTO.ReactionDTO;
import com.conexemi.emi.Exceptions.ResourceNotFoundException;
import com.conexemi.emi.model.Entrepreneurship;
import com.conexemi.emi.model.Reaction;
import com.conexemi.emi.model.User;
import com.conexemi.emi.repositories.EntrepreneurshipRepository;
import com.conexemi.emi.repositories.ReactionRepository;
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
            reaction.getIdEntrepreneurship().getEntrepreneurshipName(),
            reaction.getIdUser().getIdUser(),
            reaction.getIdUser().getFirstName() + " " + reaction.getIdUser().getLastName(),
            null

        );
    }


    // Convert from Entity to DTO
    public static ReactionDTO toDTO(Reaction reaction, ReactionRepository reactionRepository) {

        Integer idEntrepreneurship = reaction.getIdEntrepreneurship() != null? reaction.getIdEntrepreneurship().getIdEntrepreneurship(): null;
        Integer totalReactions = idEntrepreneurship != null? reactionRepository.countReactionsByEntrepreneurship(idEntrepreneurship): 0;

        return new ReactionDTO(
                reaction.getIdReaction(),
                reaction.isHasReacted(),
                idEntrepreneurship,
                reaction.getIdEntrepreneurship().getEntrepreneurshipName(),
                reaction.getIdUser().getIdUser(),
                reaction.getIdUser().getFirstName() + " " + reaction.getIdUser().getLastName(),
                totalReactions
        );
    }


    // Convert from DTO to Entity
    public static Reaction toEntity(ReactionDTO reactionDTO,
                                    EntrepreneurshipRepository entrepreneurshipRepository,
                                    UserRepository userRepository) {
        // Get related objects
        Entrepreneurship entrepreneurship = entrepreneurshipRepository.findById(reactionDTO.getIdEntrepreneurship())
                .orElseThrow(() -> new ResourceNotFoundException("Entrepreneurship not found"));
        User user = userRepository.findById(reactionDTO.getIdUser())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        // Create the Reaction entity
        Reaction reaction = new Reaction();
        reaction.setIdReaction(reactionDTO.getIdReaction());
        reaction.setHasReacted(reactionDTO.isHasReacted());
        reaction.setIdEntrepreneurship(entrepreneurship);
        reaction.setIdUser(user);
        return reaction;
    }

}
