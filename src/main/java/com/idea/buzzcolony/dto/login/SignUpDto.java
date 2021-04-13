package com.idea.buzzcolony.dto.login;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.idea.buzzcolony.dto.vimeo.FileDto;
import com.idea.buzzcolony.model.base.AppUser;
import com.idea.buzzcolony.util.Constants;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.format.DateTimeFormatter;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SignUpDto {

    private String firstName;

    private String lastName;

    private String email;

    private String phoneNo;

    private String userId;

    private String password;

    private String dateOfBirth;

    private String permanentAddress;

    private String tempAddress;

    private FileDto profilePicDto;

    private String occupation;

    private String aboutMe;

    private Boolean isActive = Boolean.TRUE;

    public SignUpDto(AppUser appUser) {
        this.firstName = appUser.getFirstName();
        this.lastName = appUser.getLastName();
        this.email = appUser.getEmail();
        this.phoneNo = appUser.getPhoneNo();
        this.userId = appUser.getUserId();
        this.dateOfBirth = appUser.getDateOfBirth().format(DateTimeFormatter.ofPattern(Constants.DATE));
        this.tempAddress = appUser.getTempAddress();
        this.permanentAddress = appUser.getPermanentAddress();
        this.aboutMe = appUser.getAboutMe();
        this.occupation = appUser.getOccupation();
    }
}
