package com.idea.buzzcolony.dto.login;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SignUpDto {

    private String firstName;

    private String lastName;

    private String email;

    private String phoneNo;

    private String userId;

    private String password;

    private String dateOfBirth;
}
