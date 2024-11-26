package com.conexemi.emi.services;

import com.conexemi.emi.model.Reaction;
import com.conexemi.emi.repositories.ReactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReactionService {

    @Autowired
    private ReactionRepository reactionRepository;

    public Reaction createReaction(Reaction reaction) {
        return reactionRepository.save(reaction);
    }

    public Optional<Reaction> getReactionById(Integer idReaction) {
        return reactionRepository.findById(idReaction);
    }

    public List<Reaction> getAllReactions() {
        return reactionRepository.findAll();
    }

    public void deleteReactionById(Integer idReaction) {
        reactionRepository.deleteById(idReaction);
    }
}
