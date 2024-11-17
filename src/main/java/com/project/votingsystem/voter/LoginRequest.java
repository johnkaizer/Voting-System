package com.project.votingsystem.voter;

import lombok.Data;

@Data
public class LoginRequest {
    private String nationalId;
    private String password;

}
