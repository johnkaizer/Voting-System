package com.project.votingsystem.results;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ResultService {

    @Autowired
    private ResultRepository resultRepository;

    // Increment vote count for a specific candidate
    public String incrementVoteCount(Long candidateId) {
        Optional<Result> result = resultRepository.findById(candidateId);

        if (result.isPresent()) {
            Result candidateResult = result.get();
            candidateResult.setVoteCount(candidateResult.getVoteCount() + 1);
            resultRepository.save(candidateResult);
            return "Vote count incremented successfully for candidate ID: " + candidateId;
        }

        return "Candidate result not found for ID: " + candidateId;
    }

    // Get all results
    public List<Result> getAllResults() {
        return resultRepository.findAll();
    }

    // Get results by position
    public List<Result> getResultsByPosition(String positionRunningFor) {
        return resultRepository.findByPositionRunningFor(positionRunningFor);
    }
}
