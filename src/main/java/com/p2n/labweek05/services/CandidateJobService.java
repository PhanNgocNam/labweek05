package com.p2n.labweek05.services;

import com.p2n.labweek05.entities.CandidateJob;
import com.p2n.labweek05.repositories.CandidateJobRepository;

import java.util.List;

public interface CandidateJobService {
    CandidateJob createCandidateJob(CandidateJob candidateJob);
    CandidateJob getCandidateJobById(Long id);
    CandidateJob updateCandidateJob(CandidateJob candidateJob);
    void deleteCandidateJob(Long id);
    List<CandidateJob> getAllCandidateJobs();
}
