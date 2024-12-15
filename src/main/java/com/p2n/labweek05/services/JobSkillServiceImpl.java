package com.p2n.labweek05.services;

import com.p2n.labweek05.entities.JobSkill;
import com.p2n.labweek05.repositories.JobSkillRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JobSkillServiceImpl implements JobSkillService {

    private final JobSkillRepository jobSkillRepository;

    @Autowired
    public JobSkillServiceImpl(JobSkillRepository jobSkillRepository) {
        this.jobSkillRepository = jobSkillRepository;
    }

    @Override
    public JobSkill createJobSkill(JobSkill jobSkill) {
        return jobSkillRepository.save(jobSkill);
    }

    @Override
    public JobSkill getJobSkillById(Long id) {
        return jobSkillRepository.findById(id).orElse(null);
    }

    @Override
    public JobSkill updateJobSkill(JobSkill jobSkill) {
        return jobSkillRepository.save(jobSkill);
    }

    @Override
    public void deleteJobSkill(Long id) {
        jobSkillRepository.deleteById(id);
    }

    @Override
    public List<JobSkill> getAllJobSkills() {
        return jobSkillRepository.findAll();
    }
}
