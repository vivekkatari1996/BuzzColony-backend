package com.idea.buzzcolony.service;

import com.idea.buzzcolony.dto.login.LoginDto;
import com.idea.buzzcolony.dto.login.SignUpDto;
import com.idea.buzzcolony.util.ApiResponse;

public interface UserService {

    ApiResponse createUser(SignUpDto signUpDto) throws Exception;

    ApiResponse login(LoginDto loginDto);

    ApiResponse socialLogin(String code);

    ApiResponse getCountries();
}
