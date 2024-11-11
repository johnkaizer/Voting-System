package com.project.votingsystem.voter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/voters")
public class VoterController {

    @Autowired
    private VoterService voterService;

    // Endpoint to register a new voter
    @PostMapping("/register")
    public ResponseEntity<String> registerVoter(@RequestBody Map<String, String> voterData) {
        String nationalId = voterData.get("nationalId");
        String name = voterData.get("name");
        String password = voterData.get("password");
        String role = voterData.get("role");

        String response = voterService.registerVoter(nationalId, name, password, role);
        return response.contains("successfully") ? ResponseEntity.ok(response) : ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }

    // Endpoint to log in a voter
    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> loginVoter(@RequestBody Map<String, String> loginData) {
        String nationalId = loginData.get("nationalId");
        String password = loginData.get("password");

        Optional<Voter> voter = voterService.loginVoter(nationalId, password);
        if (voter.isPresent()) {
            Voter loggedInVoter = voter.get();
            return ResponseEntity.ok(Map.of(
                    "message", "Login successful",
                    "name", loggedInVoter.getName(),
                    "voterId", loggedInVoter.getVoterId()
            ));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", "Invalid National ID or password."));
    }

    // Admin: Get all voters
    @GetMapping("/all")
    public ResponseEntity<List<Voter>> getAllVoters() {
        List<Voter> voters = voterService.getAllVoters();
        return ResponseEntity.ok(voters);
    }

    // Admin: Get a voter by ID
    @GetMapping("/{voterId}")
    public ResponseEntity<Voter> getVoterById(@PathVariable Long voterId) {
        Optional<Voter> voter = voterService.getVoterById(voterId);
        return voter.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Admin: Update a voter by ID
    @PutMapping("/update/{voterId}")
    public ResponseEntity<String> updateVoter(@PathVariable Long voterId, @RequestBody Map<String, String> voterData) {
        String name = voterData.get("name");
        String password = voterData.get("password");
        String role = voterData.get("role");

        String response = voterService.updateVoter(voterId, name, password, role);
        return response.contains("updated successfully") ? ResponseEntity.ok(response) : ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    // Admin: Delete a voter by ID
    @DeleteMapping("/delete/{voterId}")
    public ResponseEntity<String> deleteVoter(@PathVariable Long voterId) {
        String response = voterService.deleteVoter(voterId);
        return response.contains("deleted successfully") ? ResponseEntity.ok(response) : ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
}
