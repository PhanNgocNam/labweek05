package com.p2n.labweek05.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/company")
public class CompanyController {

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("message", "Welcome to Company Dashboard!");
        return "company/dashboard";
    }

    @GetMapping("/profile")
    public String profile(Model model) {
        model.addAttribute("message", "Company Profile Page");
        return "company/profile";
    }
}