package com.project.votingsystem.results;

import jakarta.persistence.*;

@Entity
@Table(name = "results")
public class Result {

    @Id
    private Long candidateId; // Unique ID for each candidate, same as Candidate entity ID

    @Column(nullable = false)
    private String name; // Candidate's name for easier result tracking

    @Column(nullable = false)
    private String positionRunningFor; // Position the candidate is running for

    @Column(nullable = false)
    private Integer voteCount = 0; // Vote tally for each candidate

    // Constructors, Getters, and Setters
    public Result() {}

    public Result(Long candidateId, String name, String positionRunningFor, Integer voteCount) {
        this.candidateId = candidateId;
        this.name = name;
        this.positionRunningFor = positionRunningFor;
        this.voteCount = voteCount;
    }

    public Long getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(Long candidateId) {
        this.candidateId = candidateId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPositionRunningFor() {
        return positionRunningFor;
    }

    public void setPositionRunningFor(String positionRunningFor) {
        this.positionRunningFor = positionRunningFor;
    }

    public Integer getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(Integer voteCount) {
        this.voteCount = voteCount;
    }
}

