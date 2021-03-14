package com.idea.buzzcolony.service;

import com.idea.buzzcolony.dto.login.ForgetPassDto;
import com.idea.buzzcolony.dto.login.LoginDto;
import com.idea.buzzcolony.dto.login.SignUpDto;
import com.idea.buzzcolony.util.ApiResponse;

public interface UserService {

    ApiResponse createUser(SignUpDto signUpDto) throws Exception;

    ApiResponse login(LoginDto loginDto);

    ApiResponse socialLogin(String code);

    ApiResponse getCountries();

    ApiResponse getForgetPassMail(String email) throws Exception;

    ApiResponse verifyForgetPassMail(ForgetPassDto forgetPassDto) throws Exception;

    ApiResponse sendVerifyMail(String email) throws Exception;

    ApiResponse verifyEmail(String token) throws Exception;
}
