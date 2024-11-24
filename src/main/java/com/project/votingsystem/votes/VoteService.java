package com.project.votingsystem.votes;

import com.project.votingsystem.exceptions.VoterNotRegisteredException;
import com.project.votingsystem.exceptions.AlreadyVotedException;
import com.project.votingsystem.voter.VoterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class VoteService {

    @Autowired
    private VoterRepository voterRepository;

    @Autowired
    private VoteRepository voteRepository;

    public void castVote(Long nationalId, Long candidateId, String position, String name) {
        // Step 1: Validate National ID
        if (!voterRepository.existsByNationalId(nationalId)) {
            throw new VoterNotRegisteredException("The provided National ID does not match any registered voter.");
        }

        // Step 2: Check if already voted
        boolean hasVoted = voteRepository.existsByVoterIdAndPosition(nationalId, position);
        if (hasVoted) {
            throw new AlreadyVotedException("You have already voted for this position.");
        }

        // Step 3: Create a new vote record
        Vote vote = new Vote();
        vote.setVoterId(nationalId);
        vote.setCandidateId(candidateId);
        vote.setPosition(position);
        vote.setName(name);
        vote.setTimestamp(LocalDateTime.now());

        voteRepository.save(vote);
    }

}
