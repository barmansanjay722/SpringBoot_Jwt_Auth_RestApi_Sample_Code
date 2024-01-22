package com.sundae.api.services.user;

import com.sundae.api.models.request.AddUserRequest;
import com.sundae.api.models.response.UserResponse;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public interface UserService {
    UserResponse getUserId(String userId);

    UserResponse save(AddUserRequest addUserRequest) throws NoSuchAlgorithmException, InvalidKeySpecException;
}
