package com.sundae.api.services.email;

public interface EmailService {

    void sendOTPEmail(int otp, String email);

    void sendMessageToEmail(String mesage, String email);
}
