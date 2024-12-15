package com.p2n.labweek05.services;

import com.p2n.labweek05.entities.CandidateSkill;
import com.p2n.labweek05.repositories.CandidateSkillRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CandidateSkillServiceImpl implements CandidateSkillService {

    private final CandidateSkillRepository candidateSkillRepository;

    @Autowired
    public CandidateSkillServiceImpl(CandidateSkillRepository candidateSkillRepository) {
        this.candidateSkillRepository = candidateSkillRepository;
    }

    @Override
    public CandidateSkill createCandidateSkill(CandidateSkill candidateSkill) {
        return candidateSkillRepository.save(candidateSkill);
    }

    @Override
    public CandidateSkill getCandidateSkillById(Long id) {
        return candidateSkillRepository.findById(id).orElse(null);
    }

    @Override
    public CandidateSkill updateCandidateSkill(CandidateSkill candidateSkill) {
        return candidateSkillRepository.save(candidateSkill);
    }

    @Override
    public void deleteCandidateSkill(Long id) {
        candidateSkillRepository.deleteById(id);
    }

    @Override
    public List<CandidateSkill> getAllCandidateSkills() {
        return candidateSkillRepository.findAll();
    }
}
