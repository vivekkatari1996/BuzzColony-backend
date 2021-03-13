package com.idea.buzzcolony.dto.login;

import com.idea.buzzcolony.model.base.AppUser;
import com.idea.buzzcolony.util.Constants;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.format.DateTimeFormatter;

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

    public SignUpDto(AppUser appUser) {
        this.firstName = appUser.getFirstName();
        this.lastName = appUser.getLastName();
        this.email = appUser.getEmail();
        this.phoneNo = appUser.getPhoneNo();
        this.userId = appUser.getUserId();
        this.dateOfBirth = appUser.getDateOfBirth().format(DateTimeFormatter.ofPattern(Constants.DATE));
    }
}
