package com.p2n.labweek05.services;

import com.p2n.labweek05.entities.CandidateJob;
import com.p2n.labweek05.repositories.CandidateJobRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CandidateJobServiceImpl implements CandidateJobService {

    private final CandidateJobRepository candidateJobRepository;

    @Autowired
    public CandidateJobServiceImpl(CandidateJobRepository candidateJobRepository) {
        this.candidateJobRepository = candidateJobRepository;
    }

    @Override
    public CandidateJob createCandidateJob(CandidateJob candidateJob) {
        return candidateJobRepository.save(candidateJob);
    }

    @Override
    public CandidateJob getCandidateJobById(Long id) {
        return candidateJobRepository.findById(id).orElse(null);
    }

    @Override
    public CandidateJob updateCandidateJob(CandidateJob candidateJob) {
        return candidateJobRepository.save(candidateJob);
    }

    @Override
    public void deleteCandidateJob(Long id) {
        candidateJobRepository.deleteById(id);
    }

    @Override
    public List<CandidateJob> getAllCandidateJobs() {
        return candidateJobRepository.findAll();
    }
}
