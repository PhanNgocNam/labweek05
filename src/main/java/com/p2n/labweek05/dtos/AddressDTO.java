package com.p2n.labweek05.dtos;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AddressDTO {
    private String street;
    private String city;
    private String postalCode;
    private String countryCode;
}