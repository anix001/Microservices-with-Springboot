package com.UserService.service.impl;

import com.UserService.domain.User;
import com.UserService.exception.NotFoundException;
import com.UserService.repository.UserRepository;
import com.UserService.service.EmailService;
import com.UserService.utils.OTPGenerator;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Properties;

@Service
public class EmailServiceImpl implements EmailService {
    private final UserRepository userRepository;
    private  final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final OTPGenerator otpGenerator;

    public EmailServiceImpl(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder, OTPGenerator otpGenerator) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.otpGenerator = otpGenerator;
    }

    private void mailSender(String to, String subject, String userMessage){
        //smtp properties
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.host", "smtp.gmail.com");

        String username = "barcafan830";
        String password = "pemzespckackwtdj";

        //session
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try{
            Message message = new MimeMessage(session);
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setFrom(new InternetAddress("barcafan830@gmail.com"));
            message.setSubject(subject);
            message.setContent(userMessage,"text/html");

            Transport.send(message);
        }catch (Exception e){
            throw new NotFoundException("Mail cannot be sent !!" + e);
        }
    }

    @Override
    public void verifyAccount(String to) {

        String newOtp = otpGenerator.generateOTP();
        String userVerifyLink = "http://localhost:3000/auth/verify-otp?username="+to;

        String message =  "<div style=\"font-family:Helvetica,Arial,sans-serif;font-size:16px;margin:0;color:#0b0c0c;border:3px solid #f8f8f8;padding:10px; border-radius:10px;\">\n"+
                "<p>Hello </p><b>" + to + "</b>, \n"+
                "\n"+
                "<p>Thank you for signing up with Microservices with Spring boot !! </p>\n"+
                "\n"+
                "<p>Use the following OTP to reset your account password: [ <b>" + newOtp + " </b>]</p> \n"+
                "\n"+
                "<p>To activate your account, please click the link below to confirm your email address:</p>\n" +
                "\n"+
                "<button>" +
                "<a href=\""+ userVerifyLink+"\">Verify Account</a>"+
                "</button style=\"padding:10px;color:#fff;background:#0ECA89;border-radius:10px;\">"+
                "\n"+
                "Regards,"+
                "\n"+
                "</hr>"+
                "\n"+
                "<p>&#169; Microservices with Spring boot. All rights reserved.</p>\n"+
                "\n"+
                "<p>&#169; anix001</p>"+
                "</div>"
                ;

        User user = userRepository.findByEmail(to).orElseThrow(()-> new NotFoundException("User is not found"));
        user.setOtp(bCryptPasswordEncoder.encode(newOtp));
        userRepository.save(user);
        mailSender(to, "Account Activation Email", message);
    }

    @Override
    public void forgotPassword(String to) {
        String newOtp = otpGenerator.generateOTP();
        String userVerifyLink = "http://localhost:3000/auth/verify-otp?username="+to;

        String message = "<div style=\"font-family:Helvetica,Arial,sans-serif;font-size:16px;margin:0;color:#0b0c0c;border:3px solid #f8f8f8;padding:10px; border-radius:10px;\">\n"+
                "\n"+
                "<p>Hello </p><b>" + to + "</b>, \n"+
                "\n"+
                "<p>Use the following OTP to reset your account password: [ <b>" + newOtp + " </b>]</p> \n"+
                "\n"+
                "<p>To Reset your password, please click the link below:</p>\n" +
                "\n"+
                "<button>" +
                "<a href=\""+ userVerifyLink+"\"> Reset Password</a>"+
                "</button style=\"padding:10px;color:#fff;background:#0ECA89;border-radius:10px;\">"+
                "\n"+
                "Regards,"+
                "\n"+
                "</hr>"+
                "\n"+
                "<p>&#169; Microservices with Spring boot. All rights reserved.</p>\n"+
                "\n"+
                "<p>&#169; anix001</p>"+
                "</div>";

        User user = userRepository.findByEmail(to).orElseThrow(()-> new NotFoundException("User is not found"));
        user.setOtp(bCryptPasswordEncoder.encode(newOtp));
        userRepository.save(user);
        mailSender(to, "Reset Password Email", message);
    }
}
