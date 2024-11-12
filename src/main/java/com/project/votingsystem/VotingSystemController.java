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
    @GetMapping("/dashboard_admin")
    public String dashboard_admin() {
        return "dashboard_admin";
    }
    @GetMapping("/dashboard_user")
    public String dashboard_user() {
        return "dashboard_user";
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
