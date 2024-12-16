package com.p2n.labweek05.services;

import com.p2n.labweek05.entities.Skill;
import com.p2n.labweek05.dtos.SkillDTO;
import com.p2n.labweek05.repositories.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
        return skillRepository.findById(id)
                .map(this::convertToDTO)
                .orElseThrow(() -> new RuntimeException("Skill not found"));
    }

    @Override
    public SkillDTO updateSkill(SkillDTO skillDTO) {
        Skill skill = skillRepository.findById(skillDTO.getSkillId())
                .orElseThrow(() -> new RuntimeException("Skill not found"));
        skill.setSkillName(skillDTO.getSkillName());
        return convertToDTO(skillRepository.save(skill));
    }

    @Override
    public void deleteSkill(Long id) {
        skillRepository.deleteById(id);
    }

    @Override
    public List<SkillDTO> getAllSkills() {
        List<Skill> skills = skillRepository.findAll();
        return skills.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<Skill> getSkillsByIds(List<Long> ids) {
        return skillRepository.findAllById(ids);
    }

    private SkillDTO convertToDTO(Skill skill) {
        SkillDTO dto = new SkillDTO();
        dto.setSkillId(skill.getSkillId());
        dto.setSkillName(skill.getSkillName());
        return dto;
    }
}
