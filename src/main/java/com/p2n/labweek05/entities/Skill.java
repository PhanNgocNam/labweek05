package com.p2n.labweek05.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long skillId;

    @Column(nullable = false, unique = true)
    private String skillName;
    public Skill(long skillId, String skillName) {
        this.skillId = skillId;
        this.skillName = skillName;
    }

    @OneToMany(mappedBy = "skill", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<JobSkill> jobs;

    @OneToMany(mappedBy = "skill", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<CandidateSkill> candidates;

    @ManyToMany(mappedBy = "skills")
    private Set<User> users = new HashSet<>();
}
