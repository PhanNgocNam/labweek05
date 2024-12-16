package com.p2n.labweek05.services;

import com.p2n.labweek05.dtos.JobDTO;
import com.p2n.labweek05.entities.Job;
import com.p2n.labweek05.entities.JobSkill;
import com.p2n.labweek05.entities.Skill;
import com.p2n.labweek05.repositories.CompanyRepository;
import com.p2n.labweek05.repositories.JobRepository;
import com.p2n.labweek05.repositories.SkillRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class JobServiceImpl implements JobService {

    private final JobRepository jobRepository;
    // private final CompanyRepository companyRepository;
    private final SkillRepository skillRepository;

    @Autowired
    public JobServiceImpl(JobRepository jobRepository, SkillRepository skillRepository) {
        this.jobRepository = jobRepository;
        this.skillRepository = skillRepository;
    }

    @Override
    @Transactional
    public JobDTO createJob(JobDTO jobDTO) {
        Job job = new Job();
        job.setJobTitle(jobDTO.getJobTitle());
        job.setJobDescription(jobDTO.getJobDescription());
        job.setRequiredExperience(jobDTO.getRequiredExperience());
        job.setSalary(jobDTO.getSalary());
        
        // Set company (assuming we're using a default company for now)
        // job.setCompany(companyRepository.findById(1L)
        //         .orElseThrow(() -> new EntityNotFoundException("Default company not found")));

        // Handle skills
        if (jobDTO.getSkillIds() != null && !jobDTO.getSkillIds().isEmpty()) {
            Set<JobSkill> jobSkills = new HashSet<>();
            for (Long skillId : jobDTO.getSkillIds()) {
                Skill skill = skillRepository.findById(skillId)
                        .orElseThrow(() -> new EntityNotFoundException("Skill not found with id: " + skillId));
                JobSkill jobSkill = new JobSkill();
                jobSkill.setJob(job);
                jobSkill.setSkill(skill);
                jobSkills.add(jobSkill);
            }
            job.setSkills(jobSkills);
        }

        Job savedJob = jobRepository.save(job);
        return convertToDTO(savedJob);
    }

    @Override
    public JobDTO getJobById(Long id) {
        Job job = jobRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Job not found with id: " + id));
        return convertToDTO(job);
    }

    @Override
    public Page<JobDTO> getAllJobs(Pageable pageable) {
        return jobRepository.findAll(pageable).map(this::convertToDTO);
    }

    @Override
    public Page<JobDTO> searchJobs(String keyword, Pageable pageable) {
        return jobRepository.findByJobTitleContainingIgnoreCase(keyword, pageable)
                .map(this::convertToDTO);
    }

    @Override
    public List<JobDTO> getAllActiveJobs() {
        return jobRepository.findByIsActiveTrue()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Page<JobDTO> getAllActiveJobsPaged(Pageable pageable) {
        return jobRepository.findByIsActiveTrue(pageable).map(this::convertToDTO);
    }

    @Override
    @Transactional
    public JobDTO updateJob(Long id, JobDTO jobDTO) {
        Job job = jobRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Job not found with id: " + id));
        job.setJobTitle(jobDTO.getJobTitle());
        job.setJobDescription(jobDTO.getJobDescription());
        job.setRequiredExperience(jobDTO.getRequiredExperience());
        job.setSalary(jobDTO.getSalary());
        
        // Set company (assuming we're using a default company for now)
        // job.setCompany(companyRepository.findById(1L)
        //         .orElseThrow(() -> new EntityNotFoundException("Default company not found")));

        // Handle skills
        if (jobDTO.getSkillIds() != null && !jobDTO.getSkillIds().isEmpty()) {
            Set<JobSkill> jobSkills = new HashSet<>();
            for (Long skillId : jobDTO.getSkillIds()) {
                Skill skill = skillRepository.findById(skillId)
                        .orElseThrow(() -> new EntityNotFoundException("Skill not found with id: " + skillId));
                JobSkill jobSkill = new JobSkill();
                jobSkill.setJob(job);
                jobSkill.setSkill(skill);
                jobSkills.add(jobSkill);
            }
            job.setSkills(jobSkills);
        }

        Job updatedJob = jobRepository.save(job);
        return convertToDTO(updatedJob);
    }

    @Override
    @Transactional
    public void deleteJob(Long id) {
        if (!jobRepository.existsById(id)) {
            throw new EntityNotFoundException("Job not found with id: " + id);
        }
        jobRepository.deleteById(id);
    }

    private JobDTO convertToDTO(Job job) {
        JobDTO dto = new JobDTO();
        dto.setJobTitle(job.getJobTitle());
        dto.setJobDescription(job.getJobDescription());
        dto.setRequiredExperience(job.getRequiredExperience());
        dto.setSalary(job.getSalary());
        
        // Convert skills to skill IDs
        if (job.getSkills() != null) {
            List<Long> skillIds = job.getSkills().stream()
                    .map(jobSkill -> jobSkill.getSkill().getSkillId())
                    .collect(Collectors.toList());
            dto.setSkillIds(skillIds);
        }
        
        return dto;
    }
}
