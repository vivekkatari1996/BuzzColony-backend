package com.idea.buzzcolony.controller;

import com.idea.buzzcolony.dto.client.PostDto;
import com.idea.buzzcolony.service.ClientService;
import com.idea.buzzcolony.util.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Anand Ramesh
 * @version 1.0
 * @since 19/12/20
 */
@RestController
@RequestMapping(value = "/api/v1/publisher", produces = MediaType.APPLICATION_JSON_VALUE)
public class ClientController {

    @Autowired
    private ClientService clientService;

    @PostMapping("save-post")
    public ResponseEntity<ApiResponse> saveOrUpdatePost(@RequestBody PostDto postDto) throws Exception {
        ApiResponse apiResponse = clientService.saveOrUpdatePost(postDto);
        return new ResponseEntity<>(apiResponse, apiResponse.getStatus());
    }

    @GetMapping("categories")
    public ResponseEntity<ApiResponse> getCategories() throws Exception {
        ApiResponse apiResponse = clientService.getCategories();
        return new ResponseEntity<>(apiResponse, apiResponse.getStatus());
    }

    @GetMapping("b-types")
    public ResponseEntity<ApiResponse> getBtypes() throws Exception {
        ApiResponse apiResponse = clientService.getBtypes();
        return new ResponseEntity<>(apiResponse, apiResponse.getStatus());
    }
}
