package com.p2n.labweek05.dtos;

import lombok.Data;

@Data
public class JobMatchDTO {
    private final JobDTO job;
    private final double matchPercentage;
    
    public JobMatchDTO(JobDTO job, double matchPercentage) {
        this.job = job;
        this.matchPercentage = matchPercentage;
    }
    
    // Delegate methods to JobDTO for easy access in templates
    public Long getJobId() {
        return job.getJobId();
    }
    
    public String getJobTitle() {
        return job.getJobTitle();
    }
    
    public String getJobDescription() {
        return job.getJobDescription();
    }
    
    public Integer getRequiredExperience() {
        return job.getRequiredExperience();
    }
    
    public java.math.BigDecimal getSalary() {
        return job.getSalary();
    }
}
