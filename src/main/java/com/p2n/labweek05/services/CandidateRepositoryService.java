package com.p2n.labweek05.services;

import com.p2n.labweek05.entities.Candidate;
import com.p2n.labweek05.repositories.CandidateRepository;

import java.util.List;

public interface CandidateRepositoryService {
    Candidate createCandidate(Candidate candidate);
    Candidate getCandidateById(Long id);
    Candidate updateCandidate(Candidate candidate);
    void deleteCandidate(Long id);
    List<Candidate> getAllCandidates();
}
