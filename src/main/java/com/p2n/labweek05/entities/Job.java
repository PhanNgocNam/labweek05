package com.p2n.labweek05.entities;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Data
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long jobId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    @Column(nullable = false)
    private String jobTitle;

    @Column(nullable = false)
    private String jobDescription;

    private Integer requiredExperience;
    private BigDecimal salary;

    @ManyToMany
    @JoinTable(
        name = "job_skill",
        joinColumns = @JoinColumn(name = "job_id"),
        inverseJoinColumns = @JoinColumn(name = "skill_id")
    )
    private Set<Skill> skills;

    @OneToMany(mappedBy = "job", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<CandidateJob> appliedCandidates;
}
