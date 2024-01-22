package com.sundae.api.services.email.Implementation;

import com.sundae.api.services.email.EmailService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * Service implementation for sending email, particularly used for OTP (One-Time Password) verification.
 */
@Service
@Slf4j
@AllArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender javaMailSender;

    private final Environment env;

    /**
     * Sends an email containing the provided OTP to the specified email address.
     *
     * @param otp   The One-Time Password to be sent in the email.
     * @param email The destination email address.
     */
    @Override
    public void sendOTPEmail(int otp, String email) {

        String emailUsername = env.getProperty("spring.mail.username");

        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject("Verification otp");
        message.setFrom(emailUsername);
        message.setTo(email);
        message.setText("Here's your One Time Password (OTP) " + otp + "please don't share it ");
        javaMailSender.send(message);
    }

    /**
     * Sends a verification password to the specified email address.
     *
     * @param message The generated password to be sent.
     * @param email   The recipient's email address.
     */
    @Override
    public void sendMessageToEmail(String message, String email) {

        String emailUsername = env.getProperty("spring.mail.username");

        String sendMessage = "Hello! Your generated password is:    " + message
                + "    Please keep it confidential and do not share it with anyone.";

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setSubject("Verification password");
        simpleMailMessage.setFrom(emailUsername);
        simpleMailMessage.setTo(email);
        simpleMailMessage.setText(sendMessage);
        javaMailSender.send(simpleMailMessage);
    }
}
