package com.p2n.labweek05.entities;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Data
public class CandidateJob {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long candidateJobId;

    @ManyToOne
    @JoinColumn(name = "candidate_id", nullable = false)
    private Candidate candidate;

    @ManyToOne
    @JoinColumn(name = "job_id", nullable = false)
    private Job job;

    private LocalDate applicationDate;
    private String status;
}
