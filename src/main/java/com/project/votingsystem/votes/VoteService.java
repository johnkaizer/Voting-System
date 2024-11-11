package com.project.votingsystem.votes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class VoteService {

    @Autowired
    private VoteRepository voteRepository;

    // Method to cast a vote if not already cast for the specified position
    public String castVote(String voterId, String candidateId, String position) {
        // Check if the voter has already voted for the position
        Optional<Vote> existingVote = voteRepository.findByVoterIdAndPosition(voterId, position);

        if (existingVote.isPresent()) {
            return "You have already cast your vote for " + position;
        }

        // Create and save the new vote
        Vote vote = new Vote();
        vote.setVoterId(voterId);
        vote.setCandidateId(candidateId);
        vote.setPosition(position);
        vote.setTimestamp(LocalDateTime.now());

        voteRepository.save(vote);
        return "Vote successfully cast for " + position;
    }

    // Additional methods can be added here, such as getting votes by voter, position, etc.
}
