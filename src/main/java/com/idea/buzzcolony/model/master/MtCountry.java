package com.idea.buzzcolony.model.master;

import com.idea.buzzcolony.model.base.MasterEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * @author Anand Ramesh
 * @version 1.0
 * @since 19/12/20
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
public class MtCountry extends MasterEntity {

    private String name;

    @Column(unique = true)
    private String isoCode;

    @Column(unique = true)
    private Long seq;

    @Column(columnDefinition = "boolean default true")
    private Boolean isActive = Boolean.TRUE;
}
