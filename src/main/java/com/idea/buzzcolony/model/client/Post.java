package com.idea.buzzcolony.model.client;

import com.idea.buzzcolony.model.base.AppUser;
import com.idea.buzzcolony.model.base.BaseEntity;
import com.idea.buzzcolony.model.master.MtCategory;
import com.idea.buzzcolony.model.master.MtSubBtype;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author Anand Ramesh
 * @version 1.0
 * @since 19/12/20
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
public class Post extends BaseEntity {

    @Column(columnDefinition = "TEXT")
    private String title;

    private String estimatedPrs;                    // Estimated Partners

    private Long acceptedPrs = 0l;                       // Accepted Partners

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(columnDefinition = "boolean default true")
    private Boolean isPhNoHidden = Boolean.TRUE;        // Phone number hidden

    private String occupation;

    @ManyToOne
    private MtSubBtype mtSubBtype;

    private String bType;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private PostAddress postAddress;

    @ManyToOne
    private MtCategory mtCategory;

    @ManyToOne
    private AppUser appUser;                        // post creator
}
