package com.project.votingsystem;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class VotingSystemController {

    @GetMapping("/")
    public String landing() {
        return "landing";
    }
    @GetMapping("/adminDashboard")
    public String adminDashboard() {
        return "adminDashboard";
    }
    @GetMapping("/voterDashboard")
    public String voterDashboard() {
        return "voterDashboard";
    }
    @GetMapping("/login")
    public String login() {
        return "login";
    }
    @GetMapping("/register")
    public String register() {
        return "register";
    }
    @GetMapping("/fragments/{page}")
    public String loadPage(@PathVariable String page) {
        return "fragments/" + page;
    }
}
