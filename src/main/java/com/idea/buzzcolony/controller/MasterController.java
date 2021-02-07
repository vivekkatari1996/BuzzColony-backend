package com.idea.buzzcolony.controller;

import com.idea.buzzcolony.service.UserService;
import com.idea.buzzcolony.util.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/master/")
public class MasterController {

    @Autowired
    private UserService userService;

    @GetMapping("countries")
    public ResponseEntity<ApiResponse> getCountries() {
        ApiResponse apiResponse = userService.getCountries();
        return new ResponseEntity<>(apiResponse, apiResponse.getStatus());
    }

}
