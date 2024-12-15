package com.p2n.labweek05.services;

import com.p2n.labweek05.entities.Skill;
import com.p2n.labweek05.dtos.SkillDTO;
import com.p2n.labweek05.repositories.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkillServiceImpl implements SkillService {

    private final SkillRepository skillRepository;

    @Autowired
    public SkillServiceImpl(SkillRepository skillRepository) {
        this.skillRepository = skillRepository;
    }

    @Override
    public SkillDTO createSkill(SkillDTO skillDTO) {
        var skill = new Skill();
        skill.setSkillName(skillDTO.getSkillName());
        skill = skillRepository.save(skill);
        return convertToDTO(skill);
    }

    @Override
    public SkillDTO getSkillById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getSkillById'");
    }

    @Override
    public SkillDTO updateSkill(SkillDTO skillDTO) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateSkill'");
    }

    @Override
    public void deleteSkill(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteSkill'");
    }

    @Override
    public List<SkillDTO> getAllSkills() {
        try {
            List<Skill> skills = skillRepository.findAll();
            return skills.stream()
                    .map(this::convertToDTO)
                    .toList();
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving skills", e);
        }
    }

    private SkillDTO convertToDTO(Skill skill) {
        var skillDTO = new SkillDTO();
        skillDTO.setSkillName(skill.getSkillName());
        return skillDTO;
    }
}
