package com.idea.buzzcolony.model.base;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class AppUser extends BaseEntity {

    private String firstName;

    private String lastName;

    private Boolean isActive = Boolean.TRUE;

    @Column(name = "email", unique = true)
    private String email;

    @Column(unique = true)
    private String userId;

    private String phoneNo;

    @Column(unique = true)
    private String googleId;

    @Column(nullable = false, columnDefinition = "boolean default false")
    private Boolean isPhoneNoVerified = Boolean.FALSE;

    @Column(nullable = false, columnDefinition = "boolean default false")
    private Boolean isEmailVerified = Boolean.FALSE;

    @Column(columnDefinition = "TEXT")
    private String password;
}
