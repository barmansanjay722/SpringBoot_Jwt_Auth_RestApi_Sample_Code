package com.sundae.api.services.auth.implementation;

import com.sundae.api.config.JwtHelper;
import com.sundae.api.data.entities.AccessTokens;
import com.sundae.api.data.entities.Otps;
import com.sundae.api.data.entities.Users;
import com.sundae.api.data.repos.AccesstokenRepository;
import com.sundae.api.data.repos.RolesRespository;
import com.sundae.api.data.repos.UserRepository;
import com.sundae.api.data.repos.UserRolesRepository;
import com.sundae.api.mapper.AuthMapper;
import com.sundae.api.models.request.LoginRequest;
import com.sundae.api.models.response.LoginResponse;
import com.sundae.api.services.auth.AuthService;
import com.sundae.api.services.auth.OtpService;
import com.sundae.api.utils.EncryptionUtils;
import com.sundae.api.utils.JwtTokenUtil;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    private final UserRolesRepository userRolesRepository;

    private final JwtTokenUtil jwtTokenUtil;

    private final RolesRespository rolesRespository;

    private final OtpService otpService;

    private final AccesstokenRepository accesstokenRepository;

    private final JwtHelper jwtHelper;

    /**
     * Attempts to authenticate a user by validating the provided credentials.
     *
     * @param loginRequest The login request containing the username and password.
     * @return ResponseEntity containing the LoginResponse and HTTP status.
     * @throws NoSuchAlgorithmException If the specified encryption algorithm is not available.
     * @throws InvalidKeySpecException If the provided key specification is invalid.
     */
    @Override
    public ResponseEntity<LoginResponse> login(LoginRequest loginRequest)
            throws NoSuchAlgorithmException, InvalidKeySpecException {
        log.info("Attempting login for username: {}", loginRequest.getUsername());

        Users userDetails = userRepository.findByEmail(loginRequest.getUsername());

        if (userDetails == null) {
            log.error("Username {} is not valid", loginRequest.getUsername());
            throw new RuntimeException("Username " + loginRequest.getUsername() + " is not valid");
        }
        LoginResponse loginResponse = null;

        if (!userDetails.getEmail().isEmpty()) {

            String password = EncryptionUtils.generateHash(loginRequest.getPassword(), userDetails.getPasswordSalt());
            if (password.equals(userDetails.getPasswordHash())) {

                loginResponse = handleLoginToken(userDetails.getId(), userDetails.getEmail());
            } else {
                log.error("Password not valid for user with ID: {}", userDetails.getId());
                throw new RuntimeException("Password not valid !!");
            }
        }
        log.info("Login response generated successfully");
        return new ResponseEntity<>(loginResponse, HttpStatus.OK);
    }

    /**
     * Handles the generation of a login token for the given user.
     *
     * @param userId User ID for whom the token is generated.
     * @param email  User's email for reference.
     * @return LoginResponse containing the generated token and reference code.
     */
    private LoginResponse handleLoginToken(String userId, String email) {
        log.info("Handling login token for user with ID: {}", userId);
        String generatedToken = generateToken(userId);
        Date expireIn = jwtTokenUtil.getExpirationDateFromToken(generatedToken);
        saveToken(userId, generatedToken, expireIn, true);
        String refCode = handleOtp(userId, email);
        LoginResponse loginResponse =
                LoginResponse.builder().token(generatedToken).refCode(refCode).build();
        log.info("Login token handled successfully");
        return loginResponse;
    }

    /**
     * Generates a new authentication token for the given user ID.
     *
     * @param userId User ID for whom the token is generated.
     * @return The generated authentication token.
     */
    private String generateToken(String userId) {
        Integer roleId = userRolesRepository.findByUserId(userId).getRoleId();
        String roleName = rolesRespository.findById(roleId).get().getRoleName();
        return jwtTokenUtil.generateToken(userId, roleName);
    }

    /**
     * Saves or updates an AccessTokens entity based on the provided user ID.
     *
     * @param userId   The user ID associated with the AccessTokens.
     * @param token    The access token to be saved or updated.
     * @param expireIn The expiration date of the access token.
     * @param isActive Flag indicating whether the access token is active.
     */
    private void saveToken(String userId, String token, Date expireIn, Boolean isActive) {
        AccessTokens accessTokens = AuthMapper.mapAccessTokenDataToAccessToken(token, userId, expireIn, isActive);
        Optional<AccessTokens> existingToken = accesstokenRepository.findByUserId(userId);

        existingToken.ifPresentOrElse(
                exiting -> {
                    exiting.setToken(accessTokens.getToken());
                    exiting.setIsActive(accessTokens.getIsActive());
                    exiting.setExpiry(accessTokens.getExpiry());
                    accesstokenRepository.save(exiting);
                },
                () -> accesstokenRepository.save(accessTokens));
    }

    /**
     * Handles the generation and saving of OTP (One-Time Password) for the given user.
     *
     * @param userId User ID for whom the OTP is generated.
     * @param email  User's email for reference.
     * @return Reference code associated with the generated OTP.
     */
    private String handleOtp(String userId, String email) {
        String otp = otpService.generateAndSendOtp(email);
        String refCode = otpService.randomRefCode();
        saveOTP(userId, otp, refCode);
        return refCode;
    }

    /**
     * Saves the OTP information in the database.
     *
     * @param userId  User ID for whom the OTP is saved.
     * @param otp     The generated OTP.
     * @param refCode Reference code associated with the OTP.
     */
    private void saveOTP(String userId, String otp, String refCode) {
        Otps otps = AuthMapper.mapOtpDataToOtps(refCode, otp, userId, 300, false);
        otpService.saveOtp(otps);
    }

    /**
     * Logs out the user by invalidating active access tokens associated with their ID.
     * Throws a RuntimeException if no active tokens are found for the user.
     */
    @Override
    public void logout() {
        String userId = jwtHelper.getUserId();
        log.info("Logging out user with ID: {}", userId);

        List<AccessTokens> accessTokensList = accesstokenRepository.findByUserIdAndIsActive(userId, true);

        if (accessTokensList.isEmpty()) {
            log.warn("No active tokens found for user with ID: {}", userId);
            throw new RuntimeException("Token does not exist");
        }

        log.info("Logging out user and invalidating {} token(s).", accessTokensList.size());
        accessTokensList.forEach(accessTokens -> {
            accessTokens.setIsActive(false);
            accesstokenRepository.save(accessTokens);
        });

        log.info("Logout successful for user with ID: {}", userId);
    }
}
