package com.idea.buzzcolony.dto.master;

import com.idea.buzzcolony.model.master.MtEstAmount;
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
public class MtEstAmountDto {

    private Long id;

    private String name;

    public MtEstAmountDto(MtEstAmount mtEstAmount) {
        this.id = mtEstAmount.getId();
        this.name = mtEstAmount.getName();
    }
}
