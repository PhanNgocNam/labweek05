package com.p2n.labweek05.entities;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
public class JobSkill {

    @EmbeddedId
    private JobSkillId jobSkillId;
    
    @ManyToOne
    @MapsId("jobId")
    @JoinColumn(name = "job_id", nullable = false)
    private Job job;

    
    @ManyToOne
    @MapsId("skillId")
    @JoinColumn(name = "skill_id", nullable = false)
    private Skill skill;
}
