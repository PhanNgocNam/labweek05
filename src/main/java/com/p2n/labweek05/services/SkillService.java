package com.p2n.labweek05.services;

import com.p2n.labweek05.dtos.SkillDTO;
import com.p2n.labweek05.entities.Skill;

import java.util.List;

public interface SkillService {
    SkillDTO createSkill(SkillDTO skillDTO);
    SkillDTO getSkillById(Long id);
    SkillDTO updateSkill(SkillDTO skillDTO);
    void deleteSkill(Long id);
    List<SkillDTO> getAllSkills();
    List<Skill> getSkillsByIds(List<Long> ids);
}
