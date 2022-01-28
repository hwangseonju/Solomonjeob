package com.ssafy.solomon.api.service;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

@Service
@EnableAsync
@RequiredArgsConstructor
public class EmailSendService {

    private final JavaMailSender javaMailSender;

    @Async
    public void sendEmail(String email, String authToken) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("[솔로몬접] 회원가입 이메일 인증");
        message.setText("http://localhost:8080/api/members/email/auth?email="+email+"&authToken="+authToken);

        javaMailSender.send(message);
    }
}
