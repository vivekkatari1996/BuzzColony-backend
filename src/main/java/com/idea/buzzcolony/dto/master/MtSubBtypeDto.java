package com.idea.buzzcolony.dto.master;

import com.idea.buzzcolony.model.master.MtSubBtype;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Anand Ramesh
 * @version 1.0
 * @since 07/02/21
 */
@Getter
@Setter
@NoArgsConstructor
public class MtSubBtypeDto {

    private Long id;

    private String name;

    public MtSubBtypeDto(MtSubBtype mtSubBtype) {
        this.id = mtSubBtype.getId();
        this.name = mtSubBtype.getName();
    }
}
