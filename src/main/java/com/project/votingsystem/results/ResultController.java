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

    @Autowired
    private ResultRepository resultRepository;

    // Trigger result calculation and update
    @PostMapping("/update")
    public ResponseEntity<String> updateResults() {
        resultService.updateResults();
        return ResponseEntity.ok("Results have been updated.");
    }

    // Fetch all results
    @GetMapping
    public ResponseEntity<List<Result>> getResults() {
        List<Result> results = resultRepository.findAll();
        return ResponseEntity.ok(results);
    }
}
