package com.project.votingsystem.voter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.prefs.Preferences;

@RestController
@RequestMapping("/api/voters")
public class VoterController {

    @Autowired
    private VoterService voterService;

    // Endpoint to register a new voter
    @PostMapping("/register")
    public ResponseEntity<String> registerVoter(@RequestBody Map<String, String> voterData) {
        Long nationalId = Long.valueOf(voterData.get("nationalId"));
        String name = voterData.get("name");
        String password = voterData.get("password");

        // Ensure role is always "VOTER"
        String role = "VOTER";

        // Call the service to register the voter
        String response = voterService.registerVoter(Long.valueOf(nationalId), name, password, role);

        // Return the appropriate response
        return response.contains("successfully")
                ? ResponseEntity.ok(response)
                : ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }

    @PostMapping("/login")
    public ModelAndView login(@RequestParam Long nationalId,
                              @RequestParam String password,
                              RedirectAttributes redirectAttributes) {
        // Authenticate voter
        Optional<Voter> optionalVoter = voterService.loginVoter(nationalId, password);

        if (optionalVoter.isPresent()) {
            Voter voter = optionalVoter.get();

            // Save role, ID, and national ID in preferences
            Preferences prefs = Preferences.userNodeForPackage(VoterController.class);
            prefs.putLong("loggedInVoterId", voter.getVoterId());
            prefs.put("loggedInVoterNationalId", String.valueOf(voter.getNationalId()));
            prefs.put("loggedInVoterRole", voter.getRole());

            // Redirect based on user role
            if ("ADMIN".equals(voter.getRole())) {
                return new ModelAndView(new RedirectView("/adminDashboard"));
            } else {
                redirectAttributes.addFlashAttribute("errorMessage", "Access denied: Unknown role");
                return new ModelAndView(new RedirectView("/login"));
            }
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Invalid National ID or password");
            return new ModelAndView(new RedirectView("/login"));
        }
    }


    // POST: Handle logout
    @PostMapping("/logout")
    public ModelAndView logout() {
        // Clear stored preferences
        Preferences prefs = Preferences.userNodeForPackage(VoterController.class);
        prefs.remove("loggedInVoterId");
        prefs.remove("loggedInVoterNationalId");
        prefs.remove("loggedInVoterRole");

        // Redirect to login page
        return new ModelAndView(new RedirectView("/login"));
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

        // Enforce role as VOTER during updates
        String response = voterService.updateVoter(voterId, name, password, "VOTER");
        return response.contains("updated successfully")
                ? ResponseEntity.ok(response)
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @DeleteMapping("/delete/{voterId}")
    public ResponseEntity<String> deleteVoter(@PathVariable Long voterId) {
        String response = voterService.deleteVoter(voterId);
        return response.contains("deleted successfully")
                ? ResponseEntity.ok(response)
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

}
