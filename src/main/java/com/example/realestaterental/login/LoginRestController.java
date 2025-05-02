package com.example.realestaterental.login;

import com.example.realestaterental.util.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/v1/login")
public class LoginRestController {
    private final LoginService loginService;

    @PostMapping()
    public ApiResponse<String> login(@RequestBody LoginRequest loginRequest) {
        return loginService.login(loginRequest);
    }

}