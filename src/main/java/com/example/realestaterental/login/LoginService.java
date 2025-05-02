package com.example.realestaterental.login;

import com.example.realestaterental.util.ApiResponse;
import com.example.realestaterental.util.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final AuthenticationManager authenticationManager;

//    public ApiResponse<String> login(LoginRequest loginRequest) {
//        ApiResponse<String> response;
//        if (!StringUtils.hasText(loginRequest.getUsername()) || !StringUtils.hasText(loginRequest.getPassword())) {
//            return ApiResponse.error("Username or password is empty");
//        }
//
//        try {
//            Authentication authentication = new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword());
//            Authentication authenticated = authenticationManager.authenticate(authentication);
//
//            if (!authenticated.isAuthenticated()) {
//                response = ApiResponse.error("User not authenticated");
//            } else {
//                SecurityContextHolder.getContext().setAuthentication(authenticated);
//                response = ApiResponse.success("Login successful!");
//            }
//
//        } catch (AuthenticationException e) {
//            response = ApiResponse.error("Check username or password");
//        }
//
//        return response;
//    }
    private final JwtTokenUtil jwtTokenUtil;

    public ApiResponse<String> login(LoginRequest loginRequest) {
        ApiResponse<String> response;
        if (!StringUtils.hasText(loginRequest.getUsername()) || !StringUtils.hasText(loginRequest.getPassword())) {
            return ApiResponse.error("Username or password is empty");
        }

        try {
            Authentication authentication = new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword());
            Authentication authenticated = authenticationManager.authenticate(authentication);

            if (!authenticated.isAuthenticated()) {
                response = ApiResponse.error("User not authenticated");
            } else {
                SecurityContextHolder.getContext().setAuthentication(authenticated);
                // Генерация JWT токена
                String token = jwtTokenUtil.generateToken(authenticated);
                response = ApiResponse.success(token);
            }

        } catch (AuthenticationException e) {
            response = ApiResponse.error("Check username or password");
        }

        return response;
    }

}
