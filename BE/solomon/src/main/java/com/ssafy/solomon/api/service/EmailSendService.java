package com.ssafy.solomon.api.service;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
@EnableAsync
@RequiredArgsConstructor
public class EmailSendService {

    private final JavaMailSender javaMailSender;

    @Async
    public void sendEmail(String email, String content) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, "UTF-8");
        helper.setTo(email);
        helper.setSubject("[솔로몬접] 회원가입 이메일 인증");
        helper.setText(content, true);

        javaMailSender.send(message);
    }
}
