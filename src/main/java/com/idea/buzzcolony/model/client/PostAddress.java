package com.idea.buzzcolony.model.client;

import com.idea.buzzcolony.model.base.BaseEntity;
import com.idea.buzzcolony.model.master.MtCountry;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * @author Anand Ramesh
 * @version 1.0
 * @since 19/12/20
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
public class PostAddress extends BaseEntity {

    @ManyToOne
    private MtCountry mtCountry;

    private String state;

    private String city;

    private String district;

    private String town;
}
