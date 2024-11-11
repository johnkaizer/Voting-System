package com.project.votingsystem.candidate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CandidateService {

    @Autowired
    private CandidateRepository candidateRepository;

    // Add a new candidate
    public String addCandidate(String name, String partyAffiliation, String position, byte[] image) {
        Candidate candidate = new Candidate();
        candidate.setName(name);
        candidate.setPartyAffiliation(partyAffiliation);
        candidate.setPosition(position);
        candidate.setImage(image);
        candidateRepository.save(candidate);
        return "Candidate added successfully.";
    }

    // Get all candidates
    public List<Candidate> getAllCandidates() {
        return candidateRepository.findAll();
    }

    // Get a candidate by ID
    public Optional<Candidate> getCandidateById(Long candidateId) {
        return candidateRepository.findById(candidateId);
    }

    // Update candidate details
    public String updateCandidate(Long candidateId, String name, String partyAffiliation, String position, byte[] image) {
        Optional<Candidate> existingCandidate = candidateRepository.findById(candidateId);

        if (existingCandidate.isPresent()) {
            Candidate candidate = existingCandidate.get();
            if (name != null) candidate.setName(name);
            if (partyAffiliation != null) candidate.setPartyAffiliation(partyAffiliation);
            if (position != null) candidate.setPosition(position);
            if (image != null) candidate.setImage(image);
            candidateRepository.save(candidate);
            return "Candidate updated successfully.";
        }

        return "Candidate not found.";
    }

    // Delete a candidate by ID
    public String deleteCandidate(Long candidateId) {
        if (candidateRepository.existsById(candidateId)) {
            candidateRepository.deleteById(candidateId);
            return "Candidate deleted successfully.";
        }
        return "Candidate not found.";
    }
}

