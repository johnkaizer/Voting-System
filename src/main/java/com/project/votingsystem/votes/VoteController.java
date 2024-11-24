package com.project.votingsystem.votes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/votes")
public class VoteController {

    @Autowired
    private VoteService voteService;

    @PostMapping("/cast")
    public ResponseEntity<String> castVote(@RequestBody VoteRequest voteRequest) {
        voteService.castVote(voteRequest.getVoterId(), voteRequest.getCandidateId(), voteRequest.getPosition(),voteRequest.getName());
        return ResponseEntity.ok("Vote successfully cast!");
    }
}
