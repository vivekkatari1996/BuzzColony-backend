package com.idea.buzzcolony.dto.login;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Anand Ramesh
 * @version 1.0
 * @since 14/03/21
 */
@NoArgsConstructor
@Getter
@Setter
public class ForgetPassDto {

    private String password;

    private String token;
}


