package com.conexemi.emi.controller;

import com.conexemi.emi.model.Reaction;
import com.conexemi.emi.services.ReactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/reaction")
public class ReactionController {

    @Autowired
    private ReactionService reactionService;


    @PostMapping
    public ResponseEntity<Reaction> createReaction(@RequestBody Reaction reaction) {
        Reaction saveReaction = reactionService.createReaction(reaction);
        return new ResponseEntity<>(saveReaction, HttpStatus.CREATED);
    }

    @GetMapping("/id/{idReaction}")
    public ResponseEntity<Reaction> getReactionById(@PathVariable Integer idReaction) {
        Optional<Reaction> reaction = reactionService.getReactionById(idReaction);
        return reaction.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Reaction>> getAllReactions() {
        List<Reaction> reaction = reactionService.getAllReactions();
        return new ResponseEntity<>(reaction, HttpStatus.OK);
    }

    @DeleteMapping("/id/{idReaction}")
    public ResponseEntity<Void> deleteReactionById(@PathVariable Integer idReaction) {
        reactionService.deleteReactionById(idReaction);
        return ResponseEntity.noContent().build();
    }


}
