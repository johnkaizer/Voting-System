package com.project.votingsystem.scheduler;

import com.project.votingsystem.results.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class updateResultsAutomatically {

    @Autowired
    private ResultService resultService;

    @Scheduled(fixedRate = 60000) // Runs every 1 minute
    public void updateResultsAutomatically() {
        System.out.println("Updating vote results...");
        resultService.updateResults();
        System.out.println("Vote results updated successfully!");
    }
}

