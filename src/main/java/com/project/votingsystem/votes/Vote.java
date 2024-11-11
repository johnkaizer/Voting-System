package com.project.votingsystem.votes;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "votes")
@Data
public class Vote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long voteId;

    @Column(nullable = false)
    private String voterId; // National ID or unique identifier for the Voter

    @Column(nullable = false)
    private String candidateId; // Unique identifier for the Candidate

    @Column(nullable = false)
    private String position; // Voting position, e.g., "PRESIDENT", "GOVERNOR", etc.

    @Column(nullable = false)
    private LocalDateTime timestamp;

}
