package com.p2n.labweek05.controllers;

import com.p2n.labweek05.dtos.SkillDTO;
import com.p2n.labweek05.services.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/skills")
public class SkillController {
    
    @Autowired
    private SkillService skillService;

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("skillDTO", new SkillDTO());
        return "skills/create";
    }

    @PostMapping("/create")
    public String createSkill(@ModelAttribute SkillDTO skillDTO) {
        skillService.createSkill(skillDTO);
        return "redirect:/skills";
    }

    @GetMapping
    public String listSkills(Model model) {
        List<SkillDTO> skills = skillService.getAllSkills();
        model.addAttribute("skills", skills);
        return "skills/list";
    }
}
