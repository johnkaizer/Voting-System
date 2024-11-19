package com.project.votingsystem.votes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.prefs.Preferences;

@RestController
@RequestMapping("/api/votes")
public class VoteController {

    @Autowired
    private VoteService voteService;

    // Endpoint to cast a vote
    @PostMapping("/cast")
    public ResponseEntity<String> castVote(@RequestParam String candidateId,
                                           @RequestParam String position) {
        // Retrieve voter ID from shared preferences or session
        Preferences prefs = Preferences.userNodeForPackage(VoteController.class);
        Long voterId = prefs.getLong("loggedInVoterId", 0);

        if (voterId == 0) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("You are not logged in.");
        }

        String response = voteService.castVote(String.valueOf(voterId), candidateId, position);
        if (response.contains("already cast")) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
        }
        return ResponseEntity.ok(response);
    }

    // Endpoint to check voting status for a position
    @GetMapping("/status")
    public ResponseEntity<Boolean> checkVoteStatus(@RequestParam String position) {
        Preferences prefs = Preferences.userNodeForPackage(VoteController.class);
        Long voterId = prefs.getLong("loggedInVoterId", 0);

        if (voterId == 0) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(false);
        }

        boolean hasVoted = voteService.hasVoted(String.valueOf(voterId), position);
        return ResponseEntity.ok(hasVoted);
    }
}
