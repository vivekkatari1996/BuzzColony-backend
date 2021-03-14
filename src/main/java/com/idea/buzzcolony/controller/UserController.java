package com.idea.buzzcolony.controller;

import com.idea.buzzcolony.dto.login.ForgetPassDto;
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

    @GetMapping("/forget-pass-mail")
    public ResponseEntity<ApiResponse> getForgetPassMail(@RequestParam String email) throws Exception {
        ApiResponse apiResponse = userService.getForgetPassMail(email);
        return new ResponseEntity<>(apiResponse, apiResponse.getStatus());
    }

    @PostMapping("/verify-forget-pass")
    public ResponseEntity<ApiResponse> verifyForgetPassMail(@RequestBody ForgetPassDto forgetPassDto) throws Exception {
        ApiResponse apiResponse = userService.verifyForgetPassMail(forgetPassDto);
        return new ResponseEntity<>(apiResponse, apiResponse.getStatus());
    }

    @GetMapping("/resend-verify-email")
    public ResponseEntity<ApiResponse> sendVerifyMail(@RequestParam String email) throws Exception {
        ApiResponse apiResponse = userService.sendVerifyMail(email);
        return new ResponseEntity<>(apiResponse, apiResponse.getStatus());
    }

    @PostMapping("/verify-email")
    public ResponseEntity<ApiResponse> verifyEmail(@RequestBody String token) throws Exception {
        ApiResponse apiResponse = userService.verifyEmail(token);
        return new ResponseEntity<>(apiResponse, apiResponse.getStatus());
    }

}
