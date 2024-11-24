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
    private Long id;

    @Column(nullable = false)
    private Long voterId; // National ID

    @Column(nullable = false)
    private Long candidateId; // Unique identifier for the Candidate

    private String name; // Unique identifier for the Candidate

    @Column(nullable = false)
    private String position; // Voting position, e.g., "PRESIDENT", "GOVERNOR", etc.

    @Column(nullable = false)
    private LocalDateTime timestamp;

}
