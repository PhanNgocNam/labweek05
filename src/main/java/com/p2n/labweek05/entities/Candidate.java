package com.p2n.labweek05.entities;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Data
public class Candidate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long candidateId;

    @Column(nullable = false)
    private String fullName;

    private LocalDate dob;
    private String phone;
    private String email;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "address_id", referencedColumnName = "addressId")
    private Address address;

    @ManyToMany
    @JoinTable(
        name = "candidate_skill",
        joinColumns = @JoinColumn(name = "candidate_id"),
        inverseJoinColumns = @JoinColumn(name = "skill_id")
    )
    private Set<Skill> skills;

    @ManyToMany
    @JoinTable(
        name = "candidate_job",
        joinColumns = @JoinColumn(name = "candidate_id"),
        inverseJoinColumns = @JoinColumn(name = "job_id")
    )
    private Set<Job> appliedJobs;
}
