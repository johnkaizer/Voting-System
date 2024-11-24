package com.project.votingsystem.voter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class VoterService {

    @Autowired
    private VoterRepository voterRepository;

    // Register a new voter
    public String registerVoter(Long nationalId, String name, String password, String role) {
        if (voterRepository.findByNationalId(nationalId).isPresent()) {
            return "A voter with this National ID is already registered.";
        }

        Voter voter = new Voter();
        voter.setNationalId(nationalId);
        voter.setName(name);
        voter.setPassword(password);
        voter.setRole(role);
        voterRepository.save(voter);
        return "Voter registered successfully.";
    }

    // Login voter with National ID and password
    public Optional<Voter> loginVoter(Long nationalId, String password) {
        Optional<Voter> voter = voterRepository.findByNationalId(nationalId);

        // Check if voter exists and password matches
        if (voter.isPresent() && voter.get().getPassword().equals(password)) {
            return voter;
        }

        return Optional.empty();
    }

    // Admin: Get all voters
    public List<Voter> getAllVoters() {
        return voterRepository.findAll();
    }

    // Admin: Get a voter by ID
    public Optional<Voter> getVoterById(Long voterId) {
        return voterRepository.findById(voterId);
    }

    // Admin: Update a voter's details
    public String updateVoter(Long voterId, String name, String password, String role) {
        Optional<Voter> existingVoter = voterRepository.findById(voterId);

        if (existingVoter.isPresent()) {
            Voter voter = existingVoter.get();
            if (name != null) voter.setName(name);
            if (password != null) voter.setPassword(password); // Store password as plain text
            if (role != null) voter.setRole(role);
            voterRepository.save(voter);
            return "Voter updated successfully.";
        }

        return "Voter not found.";
    }

    // Admin: Delete a voter by ID
    public String deleteVoter(Long voterId) {
        if (voterRepository.existsById(voterId)) {
            voterRepository.deleteById(voterId);
            return "Voter deleted successfully.";
        }
        return "Voter not found.";
    }
}
