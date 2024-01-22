package com.sundae.api.services.auth.implementation;

import com.sundae.api.data.entities.Otps;
import com.sundae.api.data.repos.OtpRepository;
import com.sundae.api.services.auth.OtpService;
import com.sundae.api.services.email.EmailService;
import java.util.Optional;
import java.util.Random;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

/**
 * Service implementation for OTP (One-Time Password) related operations.
 */
@Service
@Slf4j
@AllArgsConstructor
public class OtpServiceImpl implements OtpService {

    private final EmailService emailService;

    private final OtpRepository otpRepository;

    /**
     * Generates a random OTP, sends it to the specified email, and returns the generated OTP as a string.
     *
     * @param email The email address to which the OTP is sent.
     * @return The generated OTP as a string.
     */
    @Override
    public String generateAndSendOtp(String email) {
        int otp = new Random().nextInt(1000, 9999);
        emailService.sendOTPEmail(otp, email);
        return Integer.toString(otp);
    }

    /**
     * Generates a random reference code consisting of two parts separated by a hyphen.
     *
     * @return The generated random reference code.
     */
    @Override
    public String randomRefCode() {
        String rand1 = RandomStringUtils.random(3, true, false).toLowerCase();
        String rand2 = RandomStringUtils.random(3, true, false).toLowerCase();
        return rand1 + "-" + rand2;
    }

    /**
     * Saves the provided OTP information in the database.
     *
     * @param otps The OTP information to be saved.
     */
    @Override
    public void saveOtp(Otps otps) {
        if (otps != null) {
            Optional<Otps> currentOtp = otpRepository.findByUserId(otps.getUserId());
            currentOtp.ifPresentOrElse(
                    exitOtp -> {
                        exitOtp.setOtp(otps.getOtp());
                        exitOtp.setReferenceCode(otps.getReferenceCode());
                        exitOtp.setExpiresIn(300);
                        exitOtp.setIsVerified(false);
                        otpRepository.save(exitOtp);
                    },
                    () -> otpRepository.save(otps));
        }
    }
}
