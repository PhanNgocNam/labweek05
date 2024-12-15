package com.p2n.labweek05.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.p2n.labweek05.entities.Skill;

import java.util.List;

@Repository
public interface SkillRepository extends JpaRepository<Skill, Long> {
//    List<Skill> findBySkillName(String skillName);
}
