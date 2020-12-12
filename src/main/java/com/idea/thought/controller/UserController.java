package com.idea.thought.controller;

import com.idea.thought.util.ApiResponse;
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
    public ResponseEntity<ApiResponse> signUp(@RequestBody LoginRequestDto loginRequestDto) {
        ApiResponse apiResponse = userService.createUser(loginRequestDto, Role.CLIENT);
        return new ResponseEntity<>(apiResponse, apiResponse.getStatus());
    }

    @PostMapping("/login")
    public
    ResponseEntity<ApiResponse> login(@RequestBody LoginRequestDto loginData) {
        ApiResponse apiResponse = userService.processLogin(loginData);
        return new ResponseEntity<>(apiResponse, apiResponse.getStatus());
    }

    @GetMapping("/social-login")
    public ResponseEntity<ApiResponse> socialLogin(@RequestParam String code) {
        ApiResponse apiResponse = userService.socialLogin(code);
        return new ResponseEntity<>(apiResponse, apiResponse.getStatus());
    }

    @GetMapping("/send-forget-pass-mail")
    public ResponseEntity<ApiResponse> sendForgetPassCode(@RequestParam String email) {
        ApiResponse apiResponse = userService.sendForgetPassCode(email);
        return new ResponseEntity<>(apiResponse, apiResponse.getStatus());
    }

    @PostMapping("/verify-forget-pass")
    public ResponseEntity<ApiResponse> verifyForgetPassCode(@RequestBody ForgotPassDto forgotPassDto) {
        ApiResponse apiResponse = userService.verifyForgetPassCode(forgotPassDto);
        return new ResponseEntity<>(apiResponse, apiResponse.getStatus());
    }

    @GetMapping("/verify-email")
    public ResponseEntity<ApiResponse> emailVerification(@RequestParam String token) {
        ApiResponse apiResponse = userService.emailVerification(token);
        return new ResponseEntity<>(apiResponse, apiResponse.getStatus());
    }

    @GetMapping("/verify-email")
    public ResponseEntity<ApiResponse> verifyTokenExpiration(@RequestParam String token) {
        ApiResponse apiResponse = userService.verifyTokenExpiration(token);
        return new ResponseEntity<>(apiResponse, apiResponse.getStatus());
    }

}
