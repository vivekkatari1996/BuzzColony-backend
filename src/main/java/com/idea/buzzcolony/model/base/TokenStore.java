package com.idea.buzzcolony.model.base;

import com.idea.buzzcolony.enums.base.TokenType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author Anand Ramesh
 * @version 1.0
 * @since 14/03/21
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
public class TokenStore extends MasterEntity {

    @Column(columnDefinition = "TEXT", unique = true)
    private String token;

    @Enumerated(EnumType.STRING)
    private TokenType type;

    @ManyToOne
    private AppUser appUser;

    public TokenStore(String token, TokenType type, AppUser appUser) {
        this.token = token;
        this.type = type;
        this.appUser = appUser;
    }
}
