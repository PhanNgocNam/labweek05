package com.p2n.labweek05.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;

@Data
@Embeddable
public class JobSkillId implements Serializable {
    @Column(name = "job_id")
    private Long jobId;

    @Column(name = "skill_id")
    private Long skillId;
}
