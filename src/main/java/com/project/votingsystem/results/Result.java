package com.project.votingsystem.results;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "results")
@Data
public class Result {

    @Id
    private Long candidateId; // Unique ID for each candidate, same as Candidate entity ID

    @Column(nullable = false)
    private String name; // Candidate's name for easier result tracking

    @Column(nullable = false)
    private String position; // Position the candidate is running for

    @Column(nullable = false)
    private Integer voteCount = 0; // Vote tally for each candidate
}

