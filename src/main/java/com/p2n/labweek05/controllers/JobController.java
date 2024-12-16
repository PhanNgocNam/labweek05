package com.p2n.labweek05.controllers;

import com.p2n.labweek05.dtos.JobDTO;
import com.p2n.labweek05.dtos.JobMatchDTO;
import com.p2n.labweek05.entities.Skill;
import com.p2n.labweek05.entities.User;
import com.p2n.labweek05.services.JobService;
import com.p2n.labweek05.services.SkillService;
import com.p2n.labweek05.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.*;

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
    public String listJobs(@RequestParam(defaultValue = "0") int page,
                         @RequestParam(defaultValue = "10") int size,
                         Model model) {
        Page<JobDTO> jobPage = jobService.getAllActiveJobsPaged(PageRequest.of(page, size));
        model.addAttribute("jobs", jobPage);
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
    public String suggestedJobs(Model model) {
        // Get current candidate's skills
        List<JobDTO> allJobs = jobService.getAllActiveJobs();
        
        // Create sample jobs with skills
        List<JobMatchDTO> suggestedJobs = new ArrayList<>();
        
        // Sample job 1
        JobMatchDTO job1 = new JobMatchDTO();
        job1.setJobId(1L);
        job1.setJobTitle("Senior Java Developer");
        job1.setJobDescription("Chúng tôi đang tìm kiếm một Senior Java Developer có kinh nghiệm về Spring Boot, Microservices và Cloud Computing. Bạn sẽ được làm việc với các dự án lớn, công nghệ hiện đại.");
        job1.setRequiredExperience(5);
        job1.setSalary(new BigDecimal("50000000"));
        job1.setMatchPercentage(95.0);
        job1.setCompanyName("Tech Solutions Vietnam");
        
        // Sample job 2
        JobMatchDTO job2 = new JobMatchDTO();
        job2.setJobId(2L);
        job2.setJobTitle("Frontend Developer (React)");
        job2.setJobDescription("Cơ hội tuyệt vời cho Frontend Developer với kinh nghiệm React/Redux. Được làm việc trong môi trường startup năng động, nhiều cơ hội phát triển.");
        job2.setRequiredExperience(3);
        job2.setSalary(new BigDecimal("35000000"));
        job2.setMatchPercentage(85.0);
        job2.setCompanyName("Digital Innovation JSC");
        
        // Sample job 3
        JobMatchDTO job3 = new JobMatchDTO();
        job3.setJobId(3L);
        job3.setJobTitle("DevOps Engineer");
        job3.setJobDescription("Tìm kiếm DevOps Engineer có kinh nghiệm về AWS, Docker, Kubernetes. Được làm việc với team quốc tế, lương thưởng hấp dẫn.");
        job3.setRequiredExperience(4);
        job3.setSalary(new BigDecimal("45000000"));
        job3.setMatchPercentage(75.0);
        job3.setCompanyName("Cloud Tech Solutions");
        
        suggestedJobs.add(job1);
        suggestedJobs.add(job2);
        suggestedJobs.add(job3);
        
        model.addAttribute("suggestedJobs", suggestedJobs);
        
        // Add sample skills
        Set<Skill> candidateSkills = new HashSet<>();
        candidateSkills.add(new Skill(1L, "Java"));
        candidateSkills.add(new Skill(2L, "Spring Boot"));
        candidateSkills.add(new Skill(3L, "React"));
        candidateSkills.add(new Skill(4L, "Docker"));
        
        model.addAttribute("candidateSkills", candidateSkills);
        return "candidate/suggested-jobs";
    }
}
