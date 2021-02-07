package com.idea.buzzcolony.dto.master;

import com.idea.buzzcolony.model.master.MtCountry;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Anand Ramesh
 * @version 1.0
 * @since 19/12/20
 */
@Getter
@Setter
@NoArgsConstructor
public class MtCountryDto {

    private Long id;

    private String name;

    private String isoCode;

    public MtCountryDto(MtCountry mtCountry) {
        this.id = mtCountry.getId();
        this.name = mtCountry.getName();
        this.isoCode = mtCountry.getIsoCode();
    }
}
