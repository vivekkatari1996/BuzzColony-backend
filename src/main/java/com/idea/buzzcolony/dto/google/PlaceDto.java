package com.idea.buzzcolony.dto.google;

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
public class PlaceDto {

    private String placeId;

    private String value;

    public PlaceDto(String description, String placeId) {
        this.value = description;
        this.placeId = placeId;
    }
}
