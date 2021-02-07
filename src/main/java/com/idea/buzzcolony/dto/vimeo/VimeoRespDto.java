package com.idea.buzzcolony.dto.vimeo;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Anand Ramesh
 * @version 1.0
 * @since 07/02/21
 */
@Getter
@Setter
public class VimeoRespDto {

    private String uploadLink;

    private String endPoint;

    public VimeoRespDto(String link, String uploadLink) {
        this.uploadLink = uploadLink;
        this.endPoint = link;
    }
}
