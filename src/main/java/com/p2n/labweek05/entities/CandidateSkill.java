package com.p2n.labweek05.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class CandidateSkill {

    @EmbeddedId
    private CandidateSkillId candidateSkillId;
    
    @ManyToOne
    @MapsId("candidateId")
    @JoinColumn(name = "candidate_id", nullable = false)
    private Candidate candidate;

    
    @ManyToOne
    @MapsId("skillId")
    @JoinColumn(name = "skill_id", nullable = false)
    private Skill skill;
}
