package lk.courierservice.service.impl;

import lk.courierservice.dto.EmailDto;
import lk.courierservice.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * @author Thathsara Dananjaya <thathsaradananjaya@gmail.com>
 * @since 2/16/2022
 **/
@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String sender;


    @Override
    public void sendEmail(EmailDto emailDto) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(sender);
        mailMessage.setTo(emailDto.getTo());
        mailMessage.setSubject(emailDto.getSubject());
        mailMessage.setText(emailDto.getBody());

        mailSender.send(mailMessage);
    }
}
