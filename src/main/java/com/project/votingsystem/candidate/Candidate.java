package com.project.votingsystem.candidate;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "candidates")
@Data
public class Candidate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long candidateId; // Unique identifier for each candidate

    @Column(nullable = false)
    private String name; // Candidate's full name

    @Column(nullable = false)
    private String partyAffiliation; // Political party of the candidate

    @Column(nullable = false)
    private String position; // Position candidate is contesting for, e.g., "PRESIDENT", "GOVERNOR"

    @Lob
    @Column(columnDefinition = "BLOB")
    private byte[] image; // Candidate's image stored as binary data (Blob)

    @Lob
    @Column(columnDefinition = "TEXT", nullable = true)
    private String manifesto; // Candidate's manifesto (large text field)

    @Column(nullable = false)
    private int voteCount = 0; // Number of votes the candidate has received
}

