package com.idea.buzzcolony.model.master;

import com.idea.buzzcolony.enums.master.MtBTypeEnum;
import com.idea.buzzcolony.model.base.MasterEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Anand Ramesh
 * @version 1.0
 * @since 19/12/20
 */
@Getter
@Setter
@Entity
@NoArgsConstructor
public class MtBtype extends MasterEntity {

    private String name;

    @Column(unique = true)
    private Long seq;

    @Column(columnDefinition = "boolean default true")
    private Boolean isActive = Boolean.TRUE;

    @Enumerated(EnumType.STRING)
    @Column(unique = true)
    private MtBTypeEnum type;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "mtBtype")
    @OrderBy("seq")
    private List<MtSubBtype> mtSubBtypes = new ArrayList<>();
}
