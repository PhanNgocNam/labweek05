package com.p2n.labweek05.services;

import java.util.List;
import java.util.Optional;

import com.p2n.labweek05.dtos.users.CreateUserDTO;
import com.p2n.labweek05.entities.User;

public interface UserService {
    void updateUserSkills(Long userId, List<Long> skillIds);
    User createUser(CreateUserDTO createUserDTO);
    Optional<User> findByUsername(String username);
}
