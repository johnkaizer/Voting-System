package com.project.votingsystem.elections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/elections")
public class ElectionController {

    @Autowired
    private ElectionService electionService;

    @PostMapping
    public Election createElection(@RequestBody Election election) {
        return electionService.createElection(election);
    }

    @GetMapping
    public List<Election> getAllElections() {
        return electionService.getAllElections();
    }

    @GetMapping("/{id}")
    public Election getElectionById(@PathVariable Long id) {
        return electionService.getElectionById(id);
    }

    @PutMapping("/{id}")
    public Election updateElection(@PathVariable Long id, @RequestBody Election electionDetails) {
        return electionService.updateElection(id, electionDetails);
    }

    @DeleteMapping("/{id}")
    public void deleteElection(@PathVariable Long id) {
        electionService.deleteElection(id);
    }
}
