package com.idea.buzzcolony.model.master;

import com.idea.buzzcolony.enums.master.MtEstPartType;
import com.idea.buzzcolony.model.base.MasterEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 * @author Anand Ramesh
 * @version 1.0
 * @since 21/02/21
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
public class MtEstPart extends MasterEntity {

    private String name;

    @Column(unique = true)
    private Long seq;

    @Column(columnDefinition = "boolean default true")
    private Boolean isActive = Boolean.TRUE;

    @Enumerated(EnumType.STRING)
    @Column(unique = true)
    private MtEstPartType type;
}
