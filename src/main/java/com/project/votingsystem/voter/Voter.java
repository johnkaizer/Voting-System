package com.project.votingsystem.voter;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "voters")
@Data
public class Voter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long voterId; // Unique identifier for each voter

    @Column(nullable = false, unique = true)
    private String nationalId; // National ID, unique for each voter

    @Column(nullable = false)
    private String name; // Full name of the voter

    @Column(nullable = false)
    private String password; // Hashed password for security

    @Column(nullable = false)
    private String role; // e.g., "VOTER", "ADMIN"

    @Column(nullable = false)
    private boolean votingStatus = false; // Tracks if the voter has voted
}
