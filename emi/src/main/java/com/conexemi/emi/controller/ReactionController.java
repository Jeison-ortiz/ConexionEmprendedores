package com.conexemi.emi.controller;

import com.conexemi.emi.DTO.ReactionDTO;
import com.conexemi.emi.services.ReactionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/reaction")
@Validated
public class ReactionController {

    @Autowired
    private ReactionService reactionService;


    @PostMapping
    public ResponseEntity<ReactionDTO> createReaction(@Valid @RequestBody ReactionDTO reactionDTO) {
        ReactionDTO saveReactionDTO = reactionService.createReaction(reactionDTO);
        return new ResponseEntity<>(saveReactionDTO, HttpStatus.CREATED);
    }

    @GetMapping("/id/{idReaction}")
    public ResponseEntity<ReactionDTO> getReactionById(@PathVariable Integer idReaction) {
        Optional<ReactionDTO> reactionDTO = reactionService.getReactionById(idReaction);
        return reactionDTO.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<ReactionDTO>> getAllReactions() {
        List<ReactionDTO> reactionDTO = reactionService.getAllReactions();
        return new ResponseEntity<>(reactionDTO, HttpStatus.OK);
    }

    @DeleteMapping("/id/{idReaction}")
    public ResponseEntity<Void> deleteReactionById(@PathVariable Integer idReaction) {
        reactionService.deleteReactionById(idReaction);
        return ResponseEntity.noContent().build();
    }


}
