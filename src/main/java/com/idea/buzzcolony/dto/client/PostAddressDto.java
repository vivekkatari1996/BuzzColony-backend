package com.idea.buzzcolony.dto.client;

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
public class PostAddressDto {

    private Long id;

    private String state;

    private String city;

    private String district;

    private String town;

    private MtCountryDto mtCountryDto;

    public PostAddressDto(PostAddress postAddress) {
        this.id = postAddress.getId();
        this.state = postAddress.getState();
        this.city = postAddress.getCity();
        this.district = postAddress.getDistrict();
        this.town = postAddress.getTown();
        this.mtCountryDto = new MtCountryDto(postAddress.getMtCountry());
    }
}
