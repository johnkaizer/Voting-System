package com.project.votingsystem.results;

import com.project.votingsystem.votes.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ResultService {

    @Autowired
    private VoteRepository voteRepository;

    @Autowired
    private ResultRepository resultRepository;

    public void updateResults() {
        List<Object[]> voteCounts = voteRepository.findVoteCountsByCandidate();

        for (Object[] voteData : voteCounts) {
            Long candidateId = (Long) voteData[0];
            String name = (String) voteData[1]; // Candidate's name
            String position = (String) voteData[2];
            Long count = (Long) voteData[3];

            // Check if a result already exists for this candidate and position
            Result result = resultRepository.findByCandidateIdAndPosition(candidateId, position);
            if (result == null) {
                result = new Result();
                result.setCandidateId(candidateId);
                result.setName(name); // Set the name
                result.setPosition(position);
            }

            result.setVoteCount(count.intValue());
            resultRepository.save(result);
        }
    }

}
