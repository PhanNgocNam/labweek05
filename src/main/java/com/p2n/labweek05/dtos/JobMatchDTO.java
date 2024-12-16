package com.p2n.labweek05.dtos;

import com.p2n.labweek05.entities.Skill;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Set;

@Data
@NoArgsConstructor
public class JobMatchDTO {
    private Long jobId;
    private String jobTitle;
    private String jobDescription;
    private Integer requiredExperience;
    private BigDecimal salary;
    private Double matchPercentage;
    private String companyName;
    private Set<Skill> skills;

    public JobMatchDTO(JobDTO job, double matchPercentage) {
        this.jobId = job.getJobId();
        this.jobTitle = job.getJobTitle();
        this.jobDescription = job.getJobDescription();
        this.requiredExperience = job.getRequiredExperience();
        this.salary = job.getSalary();
        this.matchPercentage = matchPercentage;
    }
}
