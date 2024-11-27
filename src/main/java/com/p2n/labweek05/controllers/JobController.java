package com.p2n.labweek05.controllers;

import com.p2n.labweek05.entities.Job;
import com.p2n.labweek05.services.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/jobs")
public class JobController {

    @Autowired
    private JobService jobService;

    @PostMapping
    public ResponseEntity<Job> createJob(@RequestBody Job job) {
        Job createdJob = jobService.saveJob(job);
        return new ResponseEntity<>(createdJob, HttpStatus.CREATED);
    }
}
