package com.sundae.api.mapper;

import com.sundae.api.data.entities.AccessTokens;
import com.sundae.api.data.entities.Otps;
import java.util.Date;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class AuthMapper {

    public static AccessTokens mapAccessTokenDataToAccessToken(
            String token, String userId, Date expireIn, Boolean isActive) {
        return AccessTokens.builder()
                .token(token)
                .userId(userId)
                .expiry(expireIn)
                .isActive(isActive)
                .build();
    }

    public static Otps mapOtpDataToOtps(
            String referenceCode, String otp, String userId, Integer expireIn, Boolean isVerified) {
        return Otps.builder()
                .referenceCode(referenceCode)
                .otp(otp)
                .userId(userId)
                .expiresIn(expireIn)
                .isVerified(isVerified)
                .build();
    }
}
