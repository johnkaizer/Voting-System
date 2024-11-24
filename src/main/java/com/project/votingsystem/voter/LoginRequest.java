package com.project.votingsystem.voter;

import lombok.Data;

@Data
public class LoginRequest {
    private Long nationalId;
    private String password;

}
