package com.project.votingsystem.voter;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface VoterRepository extends JpaRepository<Voter, Long> {
    Optional<Voter> findByNationalId(Long nationalId);
    // Check if a National ID exists in the voters table
    boolean existsByNationalId(Long nationalId);
}

