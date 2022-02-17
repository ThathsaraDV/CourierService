package lk.courierservice.service.impl;

import lk.courierservice.dto.EmailDto;
import lk.courierservice.service.EmailConsumerService;
import lk.courierservice.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

/**
 * @author Thathsara Dananjaya <thathsaradananjaya@gmail.com>
 * @since 2/16/2022
 **/
@Service
public class EmailConsumerServiceImpl implements EmailConsumerService {

    @Autowired
    private EmailService emailService;

    @Override
    @KafkaListener(topics = "email",groupId = "group_id")
    public void consume(EmailDto emailDto) {
        emailService.sendEmail(emailDto);
    }
}
