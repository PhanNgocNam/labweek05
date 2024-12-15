package com.p2n.labweek05.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobDTO {
    private Long jobId;
    private String jobTitle;
    private String jobDescription;
    private Integer requiredExperience;
    private BigDecimal salary;
    private List<Long> skillIds; // List of skill IDs
    // private Long companyId;  
}
