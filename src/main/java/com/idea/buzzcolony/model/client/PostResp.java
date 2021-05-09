package com.idea.buzzcolony.model.client;

import com.idea.buzzcolony.enums.post.PostRequest;
import com.idea.buzzcolony.enums.post.PostStatus;
import com.idea.buzzcolony.model.base.AppUser;
import com.idea.buzzcolony.model.base.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author Anand Ramesh
 * @version 1.0
 * @since 06/03/21
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
public class PostResp extends BaseEntity {

    @Column(columnDefinition = "boolean default false")
    private Boolean isSaved = Boolean.FALSE;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "character varying(255) default 'NOT_YET_SENT'", nullable = false)
    private PostRequest reqStatus = PostRequest.NOT_YET_SENT;

    @ManyToOne
    private AppUser appUser;            //  Person who has responded to post

    @ManyToOne
    private Post post;

    private LocalDateTime reqSentAt;

    private LocalDateTime savedAt;

    private LocalDateTime acceptedAt;

    private LocalDateTime rejectedAt;
}
