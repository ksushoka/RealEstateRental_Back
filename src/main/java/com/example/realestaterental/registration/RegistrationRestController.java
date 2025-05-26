package com.example.realestaterental.registration;

import com.example.realestaterental.util.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping(path = "api/v1/registration", produces = "application/json")
@CrossOrigin(origins = {"http://localhost:3000", "https://realestaterental-8ce5c.firebaseapp.com/"})
@RequiredArgsConstructor
public class RegistrationRestController {
    private final RegistrationService registrationService;

    @PostMapping()
    public ApiResponse<String> register(@RequestBody RegistrationRequest request) {
        return registrationService.register(request);
    }
    @GetMapping("/confirm")
    public String confirm(@RequestParam("token") String token) {
        return registrationService.confirmToken(token);
    }
}