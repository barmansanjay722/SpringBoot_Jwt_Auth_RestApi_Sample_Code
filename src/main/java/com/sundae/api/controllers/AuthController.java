package com.sundae.api.controllers;

import com.sundae.api.models.request.LoginRequest;
import com.sundae.api.models.response.LoginResponse;
import com.sundae.api.services.auth.AuthService;
import jakarta.validation.Valid;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("v1/auth")
public class AuthController {

    private final AuthService authService;

    /**
     * User login.
     *
     * @param loginRequest request object containing the details of the login details.
     * @return return jwt access token for the user.
     */
    @PostMapping
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest loginRequest)
            throws NoSuchAlgorithmException, InvalidKeySpecException {
        log.info("login request send for {}", loginRequest);
        return authService.login(loginRequest);
    }

    /**
     * Logs out the user by delegating to the authService.
     */
    @PostMapping("/logout")
    public void logout() {
        log.info("Received request to logout");
        authService.logout();
    }
}
