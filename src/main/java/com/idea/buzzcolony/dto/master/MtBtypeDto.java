package com.idea.buzzcolony.dto.master;

import com.idea.buzzcolony.model.master.MtBtype;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Anand Ramesh
 * @version 1.0
 * @since 19/12/20
 */
@Getter
@Setter
@NoArgsConstructor
public class MtBtypeDto {

    private Long id;

    private String name;

    private List<MtSubBtypeDto> mtSubBtypeDtos = new ArrayList<>();

    public MtBtypeDto(MtBtype mtBType) {
        this.id = mtBType.getId();
        this.name = mtBType.getName();
        this.mtSubBtypeDtos = mtBType.getMtSubBtypes().stream().map(MtSubBtypeDto::new).collect(Collectors.toList());
    }
}
