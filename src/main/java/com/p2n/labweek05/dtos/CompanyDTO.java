package com.p2n.labweek05.dtos;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CompanyDTO {
    private String companyName;
    private String contactEmail;
    private String contactPhone;
    private AddressDTO addressDTO;
}
