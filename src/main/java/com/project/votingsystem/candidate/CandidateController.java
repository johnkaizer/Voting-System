package com.project.votingsystem.candidate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/candidates")
public class CandidateController {

    @Autowired
    private CandidateService candidateService;

    // Endpoint to add a new candidate
    @PostMapping("/add")
    public ResponseEntity<String> addCandidate(@RequestParam String name,
                                               @RequestParam String partyAffiliation,
                                               @RequestParam String position,
                                               @RequestParam("image") MultipartFile imageFile,
                                               @RequestParam String manifesto) {
        try {
            byte[] image = imageFile.getBytes();
            String response = candidateService.addCandidate(name, partyAffiliation, position, image,manifesto);
            return ResponseEntity.ok(response);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload image.");
        }
    }

    // Endpoint to get all candidates
    @GetMapping("/all")
    public ResponseEntity<List<Candidate>> getAllCandidates() {
        List<Candidate> candidates = candidateService.getAllCandidates();
        return ResponseEntity.ok(candidates);
    }

    // Endpoint to get a candidate by ID
    @GetMapping("/{candidateId}")
    public ResponseEntity<Candidate> getCandidateById(@PathVariable Long candidateId) {
        Optional<Candidate> candidate = candidateService.getCandidateById(candidateId);
        return candidate.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Endpoint to update a candidate by ID
    @PutMapping("/update/{candidateId}")
    public ResponseEntity<String> updateCandidate(@PathVariable Long candidateId,
                                                  @RequestParam(required = false) String name,
                                                  @RequestParam(required = false) String partyAffiliation,
                                                  @RequestParam(required = false) String position,
                                                  @RequestParam(value = "image", required = false) MultipartFile imageFile) {
        try {
            byte[] image = (imageFile != null) ? imageFile.getBytes() : null;
            String response = candidateService.updateCandidate(candidateId, name, partyAffiliation, position, image);
            return response.contains("updated successfully") ? ResponseEntity.ok(response) : ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload image.");
        }
    }

    // Endpoint to delete a candidate by ID
    @DeleteMapping("/delete/{candidateId}")
    public ResponseEntity<String> deleteCandidate(@PathVariable Long candidateId) {
        String response = candidateService.deleteCandidate(candidateId);
        return response.contains("deleted successfully") ? ResponseEntity.ok(response) : ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
}
