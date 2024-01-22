package com.sundae.api.services.auth;

import com.sundae.api.data.entities.Otps;

public interface OtpService {

    String generateAndSendOtp(String email);

    String randomRefCode();

    void saveOtp(Otps otps);
}
