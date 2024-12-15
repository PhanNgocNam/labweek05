package com.p2n.labweek05.services;

import com.p2n.labweek05.dtos.SkillDTO;

import java.util.List;

public interface SkillService {
    SkillDTO createSkill(SkillDTO skillDTO);
    SkillDTO getSkillById(Long id);
    SkillDTO updateSkill(SkillDTO skillDTO);
    void deleteSkill(Long id);
    List<SkillDTO> getAllSkills();
}
