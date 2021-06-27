package com.idea.buzzcolony.dto.ses;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Anand Ramesh
 * @version 1.0
 * @since 20/06/21
 */
@Getter
@Setter
@NoArgsConstructor
public class SesDto {

    private String name;

    private String link;

    public SesDto(String name, String link) {
        this.name = name;
        this.link = link;
    }
}
