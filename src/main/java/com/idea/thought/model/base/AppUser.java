package com.idea.thought.model.base;

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

    private String phoneNo;

    @Column(unique = true)
    private String googleId;

    @Column(nullable = false, columnDefinition = "boolean default true")
    private Boolean isPhoneNoVerified = Boolean.TRUE;

    @Column(nullable = false, columnDefinition = "boolean default false")
    private Boolean isEmailVerified = Boolean.FALSE;

    @Column(columnDefinition = "TEXT")
    private String password;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "appUser")
    private List<UserRole> userRoles = new ArrayList<>();

    public String getRoles() {
        return this.getUserRoles().stream().map(i -> i.getMtRole().getName().name()).collect(Collectors.joining(","));
    }

    public List<GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> list = new ArrayList<GrantedAuthority>();
        this.getUserRoles().stream().forEach(i -> list.add(new SimpleGrantedAuthority(i.getMtRole().getName().name())));
        return list;
    }
}
