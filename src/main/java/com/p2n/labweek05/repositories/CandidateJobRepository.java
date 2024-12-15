package com.p2n.labweek05.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.p2n.labweek05.entities.CandidateJob;

import java.util.List;

@Repository
public interface CandidateJobRepository extends JpaRepository<CandidateJob, Long> {
//    List<CandidateJob> findByJobId(Long jobId);
//    List<CandidateJob> findByCandidateId(Long candidateId);
}
