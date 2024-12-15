package com.p2n.labweek05.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.p2n.labweek05.entities.Job;

import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {
    Page<Job> findByJobTitleContainingIgnoreCase(String jobTitle, Pageable pageable);
    Page<Job> findByCompanyCompanyId(Long companyId, Pageable pageable);
    List<Job> findByIsActiveTrue();
}
