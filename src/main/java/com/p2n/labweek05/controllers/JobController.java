package com.p2n.labweek05.controllers;

import com.p2n.labweek05.dtos.JobDTO;
import com.p2n.labweek05.dtos.JobMatchDTO;
import com.p2n.labweek05.entities.Skill;
import com.p2n.labweek05.entities.User;
import com.p2n.labweek05.services.JobService;
import com.p2n.labweek05.services.SkillService;
import com.p2n.labweek05.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/jobs")
public class JobController {

    private final JobService jobService;
    private final SkillService skillService;
    private final UserService userService;

    @Autowired
    public JobController(JobService jobService, SkillService skillService, UserService userService) {
        this.jobService = jobService;
        this.skillService = skillService;
        this.userService = userService;
    }

    @GetMapping
    public String listJobs(Model model) {
        model.addAttribute("jobs", jobService.getAllActiveJobs());
        return "jobs/list";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("jobDTO", new JobDTO());
        model.addAttribute("skills", skillService.getAllSkills());
        return "jobs/form";
    }

    @PostMapping("/create")
    public String createJob(@ModelAttribute JobDTO jobDTO) {
        jobService.createJob(jobDTO);
        return "redirect:/jobs";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("jobDTO", jobService.getJobById(id));
        model.addAttribute("skills", skillService.getAllSkills());
        return "jobs/form";
    }

    @PostMapping("/edit/{id}")
    public String updateJob(@PathVariable Long id, @ModelAttribute JobDTO jobDTO) {
        jobService.updateJob(id, jobDTO);
        return "redirect:/jobs";
    }

    @GetMapping("/delete/{id}")
    public String deleteJob(@PathVariable Long id) {
        jobService.deleteJob(id);
        return "redirect:/jobs";
    }

    @GetMapping("/candidate/suggested-jobs")
    public String suggestedJobs(Model model, Principal principal) {
        if (principal == null) {
            return "redirect:/login";
        }

        // Get current candidate's skills
        User candidate = userService.findByUsername(principal.getName())
            .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        
        Set<Skill> candidateSkills = candidate.getSkills();
        List<JobDTO> allJobs = jobService.getAllActiveJobs();
        
        // Calculate job matches and sort by match percentage
        List<JobMatchDTO> suggestedJobs = allJobs.stream()
            .map(job -> {
                Set<Skill> jobSkills = job.getSkills().stream()
                    .map(js -> js.getSkill())
                    .collect(Collectors.toSet());
                
                // Calculate match percentage
                long matchingSkills = jobSkills.stream()
                    .filter(candidateSkills::contains)
                    .count();
                double matchPercentage = jobSkills.isEmpty() ? 0 :
                    (double) matchingSkills / jobSkills.size() * 100;
                
                return new JobMatchDTO(job, matchPercentage);
            })
            .filter(match -> match.getMatchPercentage() > 0) // Only show jobs with at least one matching skill
            .sorted(Comparator.comparing(JobMatchDTO::getMatchPercentage).reversed())
            .collect(Collectors.toList());
        
        model.addAttribute("candidateSkills", candidateSkills);
        model.addAttribute("suggestedJobs", suggestedJobs);
        return "candidate/suggested-jobs";
    }
}
