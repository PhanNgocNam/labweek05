package com.p2n.labweek05.services;

import com.p2n.labweek05.dtos.JobDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface JobService {
    JobDTO createJob(JobDTO jobDTO);
    JobDTO getJobById(Long id);
    Page<JobDTO> getAllJobs(Pageable pageable);
    List<JobDTO> getAllActiveJobs();
    Page<JobDTO> searchJobs(String keyword, Pageable pageable);
    JobDTO updateJob(Long id, JobDTO jobDTO);
    void deleteJob(Long id);
}
