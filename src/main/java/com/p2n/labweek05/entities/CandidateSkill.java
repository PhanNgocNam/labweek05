package com.p2n.labweek05.entities;

import jakarta.persistence.*;
import lombok.Data;
import java.io.Serializable;

@Entity
@IdClass(CandidateSkillId.class)
@Data
public class CandidateSkill {
    @Id
    @ManyToOne
    @JoinColumn(name = "candidate_id", nullable = false)
    private Candidate candidate;

    @Id
    @ManyToOne
    @JoinColumn(name = "skill_id", nullable = false)
    private Skill skill;
}
