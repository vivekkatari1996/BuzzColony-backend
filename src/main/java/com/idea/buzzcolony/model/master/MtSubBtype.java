package com.idea.buzzcolony.model.master;

import com.idea.buzzcolony.enums.master.MtBTypeEnum;
import com.idea.buzzcolony.enums.master.MtSubBtypeEnum;
import com.idea.buzzcolony.model.base.MasterEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author Anand Ramesh
 * @version 1.0
 * @since 07/02/21
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
public class MtSubBtype extends MasterEntity {

    private String name;

    @Column(unique = true)
    private Long seq;

    @Column(columnDefinition = "boolean default true")
    private Boolean isActive = Boolean.TRUE;

    @Enumerated(EnumType.STRING)
    @Column(unique = true)
    private MtSubBtypeEnum type;

    @ManyToOne
    private MtBtype mtBtype;
}
