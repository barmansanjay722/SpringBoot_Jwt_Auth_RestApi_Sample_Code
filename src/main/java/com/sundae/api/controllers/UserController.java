package com.sundae.api.controllers;

import com.sundae.api.models.request.AddUserRequest;
import com.sundae.api.models.response.UserResponse;
import com.sundae.api.services.user.UserService;
import jakarta.validation.Valid;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("v1/systemUser")
public class UserController {
    private final UserService userService;

    /**
     * Saves a new User record.
     *
     * @param addUserRequest The request object containing the details of the SIP to be added.
     * @return A string representation of the saved SIP record.
     */
    @PostMapping
    public ResponseEntity<UserResponse> save(@Valid @RequestBody AddUserRequest addUserRequest)
            throws NoSuchAlgorithmException, InvalidKeySpecException {
        log.info("Saving User: {}", addUserRequest);
        UserResponse addUserResponse = userService.save(addUserRequest);
        return new ResponseEntity<>(addUserResponse, HttpStatus.CREATED);
    }
}
