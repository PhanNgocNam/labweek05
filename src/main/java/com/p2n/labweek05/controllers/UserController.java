package com.p2n.labweek05.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.p2n.labweek05.dtos.UpdateSkillsDTO;
import com.p2n.labweek05.dtos.users.CreateUserDTO;
import com.p2n.labweek05.entities.User;
import com.p2n.labweek05.services.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
    
    @Autowired
    private UserService userService;
    
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody CreateUserDTO createUserDTO) {
        userService.createUser(createUserDTO);
        return ResponseEntity.ok().build();
    }
    
    @PutMapping("/{userId}/skills")
    public ResponseEntity<Void> updateUserSkills(
            @PathVariable Long userId,
            @RequestBody UpdateSkillsDTO updateSkillsDTO) {
        userService.updateUserSkills(userId, updateSkillsDTO.getSkillIds());
        return ResponseEntity.ok().build();
    }
}