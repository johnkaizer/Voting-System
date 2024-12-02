package com.project.votingsystem.config;

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
    @GetMapping("/login")
    public String login() {
        return "login";
    }
    @GetMapping("/register")
    public String register() {
        return "register";
    }
    @GetMapping("/results")
    public String results() {
        return "results";
    }
    @GetMapping("/fragments/{page}")
    public String loadPage(@PathVariable String page) {
        return "fragments/" + page;
    }
}
