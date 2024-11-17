package com.project.votingsystem.elections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ElectionService {

    @Autowired
    private ElectionRepository electionRepository;

    public Election createElection(Election election) {
        return electionRepository.save(election);
    }

    public List<Election> getAllElections() {
        return electionRepository.findAll();
    }

    public Election getElectionById(Long id) {
        return electionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Election not found with ID: " + id));
    }

    public Election updateElection(Long id, Election electionDetails) {
        Election existingElection = getElectionById(id);
        existingElection.setTitle(electionDetails.getTitle());
        existingElection.setDescription(electionDetails.getDescription());
        existingElection.setStartDateTime(electionDetails.getStartDateTime());
        existingElection.setEndDateTime(electionDetails.getEndDateTime());
        existingElection.setStatus(electionDetails.getStatus());
        return electionRepository.save(existingElection);
    }

    public void deleteElection(Long id) {
        Election existingElection = getElectionById(id);
        electionRepository.delete(existingElection);
    }
}
