package com.p2n.labweek05.services;

import com.p2n.labweek05.entities.JobSkill;
import com.p2n.labweek05.repositories.JobSkillRepository;

import java.util.List;

public interface JobSkillService {
    JobSkill createJobSkill(JobSkill jobSkill);
    JobSkill getJobSkillById(Long id);
    JobSkill updateJobSkill(JobSkill jobSkill);
    void deleteJobSkill(Long id);
    List<JobSkill> getAllJobSkills();
}
