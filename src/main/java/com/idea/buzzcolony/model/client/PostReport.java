package com.idea.buzzcolony.model.client;

import com.idea.buzzcolony.model.base.AppUser;
import com.idea.buzzcolony.model.base.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * @author Anand Ramesh
 * @version 1.0
 * @since 25/04/21
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
public class PostReport extends BaseEntity {

    @ManyToOne
    private Post post;

    @ManyToOne
    private AppUser appUser;    //  person who has reported

    @Column(columnDefinition = "TEXT")
    private String reason;

    public PostReport(Post post, AppUser appUser, String reason) {
        this.post = post;
        this.appUser = appUser;
        this.reason = reason;
    }
}
