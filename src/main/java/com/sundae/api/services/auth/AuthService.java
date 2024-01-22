package com.sundae.api.services.auth;

import com.sundae.api.models.request.LoginRequest;
import com.sundae.api.models.response.LoginResponse;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    ResponseEntity<LoginResponse> login(LoginRequest loginRequest)
            throws NoSuchAlgorithmException, InvalidKeySpecException;

    void logout();
}
