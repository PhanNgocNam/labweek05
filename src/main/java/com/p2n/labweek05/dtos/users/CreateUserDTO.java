package com.p2n.labweek05.dtos.users;

import lombok.Data;

@Data
public class CreateUserDTO {
    private String username;
    private String password;
    private String email;
}
