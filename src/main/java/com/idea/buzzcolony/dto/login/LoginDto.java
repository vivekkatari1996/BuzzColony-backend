package com.idea.buzzcolony.dto.login;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LoginDto {

    private String userId;

    private String password;

    private String token;

    private String email;

    private String profilePicUrl;
}
