package com.project.votingsystem.votes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class VoteService {

    @Autowired
    private VoteRepository voteRepository;

    public String castVote(String voterId, String candidateId, String position) {
        Optional<Vote> existingVote = voteRepository.findByVoterIdAndPosition(voterId, position);

        if (existingVote.isPresent()) {
            return "You have already cast your vote for " + position;
        }

        Vote vote = new Vote();
        vote.setVoterId(voterId);
        vote.setCandidateId(candidateId);
        vote.setPosition(position);
        vote.setTimestamp(LocalDateTime.now());

        voteRepository.save(vote);
        return "Vote successfully cast for " + position;
    }

    public boolean hasVoted(String voterId, String position) {
        return voteRepository.findByVoterIdAndPosition(voterId, position).isPresent();
    }
}
