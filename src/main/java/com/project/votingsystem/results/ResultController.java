package com.project.votingsystem.results;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/results")
public class ResultController {

    @Autowired
    private ResultService resultService;

    // Endpoint to increment vote count for a candidate
    @PostMapping("/increment/{candidateId}")
    public ResponseEntity<String> incrementVoteCount(@PathVariable Long candidateId) {
        String response = resultService.incrementVoteCount(candidateId);
        return response.contains("incremented successfully") ? ResponseEntity.ok(response) : ResponseEntity.status(404).body(response);
    }

    // Endpoint to get all results
    @GetMapping("/all")
    public ResponseEntity<List<Result>> getAllResults() {
        List<Result> results = resultService.getAllResults();
        return ResponseEntity.ok(results);
    }

    // Endpoint to get results by position
    @GetMapping("/position/{position}")
    public ResponseEntity<List<Result>> getResultsByPosition(@PathVariable String position) {
        List<Result> results = resultService.getResultsByPosition(position);
        return results.isEmpty() ? ResponseEntity.status(404).body(null) : ResponseEntity.ok(results);
    }
}

