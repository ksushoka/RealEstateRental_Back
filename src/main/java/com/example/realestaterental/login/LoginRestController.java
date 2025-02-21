package com.example.realestaterental.login;

import com.example.realestaterental.util.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/login")
public class LoginRestController {
    private final LoginService loginService;

    @PostMapping()
    public ApiResponse<String> login(@RequestBody LoginRequest loginRequest) {
        return loginService.login(loginRequest);
    }

}