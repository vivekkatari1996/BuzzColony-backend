package com.idea.buzzcolony.dto.master;

import com.idea.buzzcolony.model.master.MtCategory;
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
public class MtCategoryDto {

    private Long id;

    private String name;

    public MtCategoryDto(MtCategory mtCategory) {
        this.id = mtCategory.getId();
        this.name = mtCategory.getName();
    }
}
