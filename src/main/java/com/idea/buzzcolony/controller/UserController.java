package com.idea.buzzcolony.controller;

import com.idea.buzzcolony.dto.login.LoginDto;
import com.idea.buzzcolony.dto.login.SignUpDto;
import com.idea.buzzcolony.service.UserService;
import com.idea.buzzcolony.util.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/user", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<ApiResponse> signUp(@RequestBody SignUpDto signUpDto) throws Exception {
        ApiResponse apiResponse = userService.createUser(signUpDto);
        return new ResponseEntity<>(apiResponse, apiResponse.getStatus());
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse> login(@RequestBody LoginDto loginDto) {
        ApiResponse apiResponse = userService.login(loginDto);
        return new ResponseEntity<>(apiResponse, apiResponse.getStatus());
    }

//    @GetMapping("/google-login")
    public ResponseEntity<ApiResponse> socialLogin(@RequestParam String code) {
        ApiResponse apiResponse = userService.socialLogin(code);
        return new ResponseEntity<>(apiResponse, apiResponse.getStatus());
    }

}
