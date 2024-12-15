package com.p2n.labweek05.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.p2n.labweek05.entities.CandidateSkill;

import java.util.List;

@Repository
public interface CandidateSkillRepository extends JpaRepository<CandidateSkill, Long> {
//    List<CandidateSkill> findByCandidateId(Long candidateId);
//    List<CandidateSkill> findBySkillId(Long skillId);
}
