package com.idea.buzzcolony.dto.client;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.idea.buzzcolony.dto.master.MtCountryDto;
import com.idea.buzzcolony.model.client.PostAddress;
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
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class PostAddressDto {

    private Long id;

    private String city;

    private Long mtCountryId;

    private String countryName;

    private String placeId;

    public PostAddressDto(PostAddress postAddress) {
        this.id = postAddress.getId();
        this.city = postAddress.getCity();
        this.mtCountryId = postAddress.getMtCountry().getId();
        this.countryName = postAddress.getMtCountry().getName();
    }
}
