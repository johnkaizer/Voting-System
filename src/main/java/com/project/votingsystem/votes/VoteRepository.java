package com.project.votingsystem.votes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {

    boolean existsByVoterIdAndPosition(Long  nationalId, String position);

    @Query("SELECT v.candidateId, c.name, v.position, COUNT(v) " +
            "FROM Vote v JOIN Candidate c ON v.candidateId = c.candidateId " +
            "GROUP BY v.candidateId, c.name, v.position")
    List<Object[]> findVoteCountsByCandidate();

}
