package com.idea.buzzcolony.dto.master;

import com.idea.buzzcolony.model.master.MtEstPart;
import com.idea.buzzcolony.model.master.MtSubBtype;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Anand Ramesh
 * @version 1.0
 * @since 21/02/21
 */
@Getter
@Setter
@NoArgsConstructor
public class MtEstPartDto {

    private Long id;

    private String name;

    public MtEstPartDto(MtEstPart mtEstPart) {
        this.id = mtEstPart.getId();
        this.name = mtEstPart.getName();
    }
}
