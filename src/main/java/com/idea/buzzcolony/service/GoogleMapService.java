package com.idea.buzzcolony.service;

import com.idea.buzzcolony.util.ApiResponse;

/**
 * @author Anand Ramesh
 * @version 1.0
 * @since 20/06/21
 */
public interface GoogleMapService {

    ApiResponse getCitiesBasedOnCountry(String country, String city);

}
