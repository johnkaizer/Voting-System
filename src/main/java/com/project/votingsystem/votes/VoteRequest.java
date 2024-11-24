package com.project.votingsystem.votes;

import lombok.Data;

@Data
public class VoteRequest {
    private Long voterId;      // National ID of the voter
    private Long candidateId;  // ID of the candidate being voted for
    private String position;
    private String name;
}
