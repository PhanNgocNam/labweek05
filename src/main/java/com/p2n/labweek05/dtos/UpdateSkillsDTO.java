package com.p2n.labweek05.dtos;

import java.util.List;

import lombok.Data;

@Data
public class UpdateSkillsDTO {
    private List<Long> skillIds;
}
