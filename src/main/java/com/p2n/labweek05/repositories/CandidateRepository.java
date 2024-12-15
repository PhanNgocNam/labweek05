package com.p2n.labweek05.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.p2n.labweek05.entities.Candidate;

import java.util.List;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate, Long> {
//    List<Candidate> findByFirstName(String firstName);
//    List<Candidate> findByLastName(String lastName);
}
