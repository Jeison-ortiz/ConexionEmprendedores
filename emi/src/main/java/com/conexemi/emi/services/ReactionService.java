package com.conexemi.emi.services;

import com.conexemi.emi.DTO.ReactionDTO;
import com.conexemi.emi.Exceptions.ResourceNotFoundException;
import com.conexemi.emi.Mapper.ReactionMapper;
import com.conexemi.emi.model.Reaction;
import com.conexemi.emi.repositories.EntrepreneurshipRepository;
import com.conexemi.emi.repositories.ReactionRepository;
import com.conexemi.emi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReactionService {

    @Autowired
    private ReactionRepository reactionRepository;

    @Autowired
    private EntrepreneurshipRepository entrepreneurshipRepository;

    @Autowired
    private UserRepository userRepository;


    public ReactionDTO createReaction(ReactionDTO reactionDTO) {
        Reaction reaction = ReactionMapper.toEntity(reactionDTO, entrepreneurshipRepository, userRepository);
        Reaction savedReaction = reactionRepository.save(reaction);
        return ReactionMapper.toDTO(savedReaction);
    }

    public Optional<ReactionDTO> getReactionById(Integer idReaction) {
        Optional<Reaction> reaction = Optional.ofNullable(reactionRepository.findById(idReaction)
                .orElseThrow(() -> new ResourceNotFoundException("Reaction with ID " + idReaction + " not found")));
        return reaction.map(ReactionMapper::toDTO);
    }

    public List<ReactionDTO> getAllReactions() {
        List<Reaction> reactions = reactionRepository.findAll();
        return reactions.stream().map(ReactionMapper::toDTO).collect(Collectors.toList());
    }

    public void deleteReactionById(Integer idReaction) {
        reactionRepository.deleteById(idReaction);
    }


}
