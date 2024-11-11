package com.project.votingsystem.votes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/votes")
public class VoteController {

    @Autowired
    private VoteService voteService;

    // Endpoint to cast a vote
    @PostMapping("/cast")
    public ResponseEntity<String> castVote(@RequestParam String voterId,
                                           @RequestParam String candidateId,
                                           @RequestParam String position) {
        String response = voteService.castVote(voterId, candidateId, position);
        if (response.contains("already cast")) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
        }
        return ResponseEntity.ok(response);
    }
}
