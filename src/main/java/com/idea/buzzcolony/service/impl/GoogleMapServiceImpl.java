package com.idea.buzzcolony.service.impl;

import com.idea.buzzcolony.dto.google.PlaceDto;
import com.idea.buzzcolony.service.GoogleMapService;
import com.idea.buzzcolony.util.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import se.walkercrou.places.GooglePlaces;
import se.walkercrou.places.Param;
import se.walkercrou.places.Prediction;
import se.walkercrou.places.exception.GooglePlacesException;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Anand Ramesh
 * @version 1.0
 * @since 20/06/21
 */
@Service
public class GoogleMapServiceImpl implements GoogleMapService {

    @Override
    public ApiResponse getCitiesBasedOnCountry(String country, String city) {
        try {
            if (city.length() >= 3) {
                GooglePlaces client = new GooglePlaces("AIzaSyCawXBYsu9qrv3wNW6DdBttKFdyx3JReFM");
                List<Prediction> predictions = client.getPlacePredictions
                        (city, Param.name("type").value("(cities)"),Param.name("components").value("country:"+country));
                return new ApiResponse(HttpStatus.OK, "success", predictions.stream().map(i -> new PlaceDto(i.getDescription(), i.getPlaceId())).collect(Collectors.toList()));
            } else {
                return new ApiResponse(HttpStatus.OK, "atleast three characters are required", null);
            }
        } catch (GooglePlacesException e){
            return new ApiResponse(HttpStatus.BAD_REQUEST, e.getCause().getMessage(), null);
        }
    }

}
