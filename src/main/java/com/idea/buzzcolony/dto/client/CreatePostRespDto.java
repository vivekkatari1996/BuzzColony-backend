package com.idea.buzzcolony.dto.client;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Anand Ramesh
 * @version 1.0
 * @since 02/05/21
 */
@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreatePostRespDto {

    private Long id;

    private String uploadLink;

    public CreatePostRespDto(Long id, String uploadLink) {
        this.id = id;
        this.uploadLink = uploadLink;
    }
}
