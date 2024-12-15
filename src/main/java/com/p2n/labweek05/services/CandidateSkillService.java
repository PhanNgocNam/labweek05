package com.p2n.labweek05.services;

import com.p2n.labweek05.entities.CandidateSkill;
import com.p2n.labweek05.repositories.CandidateSkillRepository;

import java.util.List;

public interface CandidateSkillService {
    CandidateSkill createCandidateSkill(CandidateSkill candidateSkill);
    CandidateSkill getCandidateSkillById(Long id);
    CandidateSkill updateCandidateSkill(CandidateSkill candidateSkill);
    void deleteCandidateSkill(Long id);
    List<CandidateSkill> getAllCandidateSkills();
}
