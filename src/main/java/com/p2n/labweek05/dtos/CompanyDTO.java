package com.p2n.labweek05.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyDTO {
    private Long companyId;
    private String companyName;
    private String contactEmail;
    private String contactPhone;
}
