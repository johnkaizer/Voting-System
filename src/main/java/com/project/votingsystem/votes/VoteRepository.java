package com.project.votingsystem.votes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {
    // Check if a voter has already voted for a specific position
    Optional<Vote> findByVoterIdAndPosition(String voterId, String position);
}
